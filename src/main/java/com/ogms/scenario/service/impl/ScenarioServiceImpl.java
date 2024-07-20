package com.ogms.scenario.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.converter.ScenarioConverter;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.mapper.ScenarioMapper;
import com.ogms.scenario.service.IRoomService;
import com.ogms.scenario.service.IScenarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @name: ScenarioServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:31 PM
 * @version: 1.0
 */
@Service
public class ScenarioServiceImpl extends ServiceImpl<ScenarioMapper, Scenario> implements IScenarioService {
    @Autowired
    private ScenarioMapper scenarioMapper;

    @Autowired
    IRoomService roomService;

    @Resource
    private ScenarioConverter scenarioConverter;

    @Override
    public List<ScenarioVo> getAllScenario() {
        QueryWrapper<Scenario> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        List<ScenarioVo> scenarioVoList = scenarioConverter.poList2VoList(scenarioMapper.selectList(queryWrapper));
        return scenarioVoList;
    }

    @Override
    public List<ScenarioVo> getAllScenarioByProjectId(Integer id) {
        QueryWrapper<Scenario> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("project_id", id);
        List<ScenarioVo> scenarioVoList = scenarioConverter.poList2VoList(scenarioMapper.selectList(queryWrapper));
        return scenarioVoList;
    }

    @Override
    public BaseResultDto saveScenarioGraphJsonById(Integer id, String graph) {
        String filePath = Constants.GRAPH_JSON_PATH + File.separator + id.toString() + ".json";
        try {
            Files.createDirectories(Paths.get(Constants.GRAPH_JSON_PATH));
            Files.write(Paths.get(filePath), graph.getBytes(StandardCharsets.UTF_8));
            return new BaseResultDto(true, true);
        } catch (IOException e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
    }

    @Override
    public BaseResultDto getScenarioGraphJsonById(Integer id) {
        String filePath = Constants.GRAPH_JSON_PATH + File.separator + id.toString() + ".json";

        File file = new File(filePath);
        if (!file.exists()) {
            return new BaseResultDto<>(true, null);
        }

        try {
            String content = new String(Files.readAllBytes(file.toPath()));

            Object json = JSON.parseObject(content);
            // 可以将 JSON 对象转为 HashMap
            // HashMap<String, Object> map = json.toMap();

            // 在这里可以处理 json 对象，比如打印内容
            return new BaseResultDto<>(true, json);
        } catch (IOException e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
    }

    @Override
    public BaseResultDto addScenario(Integer createUserId, ScenarioAddDto scenarioAddDto) {
        try {
            Scenario scenario = scenarioConverter.dto2Po(scenarioAddDto);
            scenario.setCreateUserId(createUserId);
            scenarioMapper.insert(scenario);
            scenario = scenarioMapper.selectById(scenario.getId());
            // 直接添加初始协作室
            RoomAddDto roomAddDto = new RoomAddDto(createUserId + ",", PermissionLevelEnum.write, scenario.getId(), "");
            roomService.addRoom(createUserId, roomAddDto);
            return new BaseResultDto<>(true, scenarioConverter.po2Vo(scenario));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchAddScenario(Integer createUserId, List<ScenarioAddDto> scenarioAddDtoList) {
        if (scenarioAddDtoList.isEmpty()) {
            // selectBatchIds如果传入空的List会报错
            return new BaseResultDto<>(true, null);
        }
        try {
            List<Scenario> scenarioList = scenarioConverter.dtoList2PoList(scenarioAddDtoList);
            for (Scenario scenario : scenarioList) {
                scenario.setCreateUserId(createUserId);
            }
            saveBatch(scenarioList);
            List<Integer> idList = scenarioList.stream().map(Scenario::getId).collect(Collectors.toList());
            // 添加初始协作室
            for (Integer id : idList) {
                RoomAddDto roomAddDto = new RoomAddDto(createUserId + ",", PermissionLevelEnum.write, id, "");
                roomService.addRoom(createUserId, roomAddDto);
            }
            return new BaseResultDto<>(true, scenarioConverter.poList2VoList(scenarioMapper.selectBatchIds(idList)));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto editScenario(Integer modifyUserId, ScenarioEditDto scenarioEditDto) {
        try {
            Scenario scenario = this.getById(scenarioEditDto.getId());
            BeanUtils.copyProperties(scenarioEditDto, scenario, "id");
            scenario.setModifyUserId(modifyUserId);
            scenarioMapper.updateById(scenario);
            return new BaseResultDto<>(true, scenarioConverter.po2Vo(scenario));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchEditScenario(Integer modifyUserId, List<ScenarioEditDto> scenarioEditDtoList) {
        Boolean flag = true;
        List<ScenarioVo> scenarioVoList = new ArrayList<>();
        for (ScenarioEditDto scenarioEditDto : scenarioEditDtoList) {
            BaseResultDto editResultDto = editScenario(modifyUserId, scenarioEditDto);
            if (!editResultDto.getStatus()) {
                flag = false;
                scenarioVoList.clear();
                break;
            } else {
                scenarioVoList.add((ScenarioVo) editResultDto.getResult());
            }
        }
        return new BaseResultDto<>(flag, scenarioVoList);
    }

    @Override
    public BaseResultDto delScenario(Integer id) {
        try {
            Scenario scenario = this.getById(id);
            scenario.setIsDeleted(true);
            scenarioMapper.updateById(scenario);
            return new BaseResultDto<>(true, true);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchDelScenario(List<Integer> idList) {
        Boolean flag = true;
        for (Integer id : idList) {
            if (!delScenario(id).getStatus()) {
                flag = false;
                break;
            }
        }
        return new BaseResultDto<>(flag, flag);
    }
}

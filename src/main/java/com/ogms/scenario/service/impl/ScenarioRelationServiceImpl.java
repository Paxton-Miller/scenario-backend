package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.converter.ScenarioRelationConverter;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationAddDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationEditDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.entity.ScenarioRelation;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.domain.vo.scenarioRelation.ScenarioRelationVo;
import com.ogms.scenario.mapper.ScenarioRelationMapper;
import com.ogms.scenario.service.IScenarioRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: ScenarioRelationServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:34 PM
 * @version: 1.0
 */
@Service
public class ScenarioRelationServiceImpl extends ServiceImpl<ScenarioRelationMapper, ScenarioRelation> implements IScenarioRelationService {
    @Autowired
    private ScenarioRelationMapper scenarioRelationMapper;

    @Resource
    private ScenarioRelationConverter scenarioRelationConverter;

    @Override
    public List<ScenarioRelationVo> getAllScenarioRelation() {
        QueryWrapper<ScenarioRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        List<ScenarioRelationVo> scenarioRelationVoList = scenarioRelationConverter.poList2VoList(scenarioRelationMapper.selectList(queryWrapper));
        return scenarioRelationVoList;
    }

    @Override
    public List<ScenarioRelationVo> getAllScenarioRelationByProjectId(Integer id) {
        QueryWrapper<ScenarioRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("project_id", id);
        List<ScenarioRelationVo> scenarioRelationVoList = scenarioRelationConverter.poList2VoList(scenarioRelationMapper.selectList(queryWrapper));
        return scenarioRelationVoList;
    }

    @Override
    public BaseResultDto addScenarioRelation(Integer createUserId, ScenarioRelationAddDto scenarioRelationAddDto) {
        try {
            ScenarioRelation scenarioRelation = scenarioRelationConverter.dto2Po(scenarioRelationAddDto);
            scenarioRelation.setCreateUserId(createUserId);
            scenarioRelationMapper.insert(scenarioRelation);
            scenarioRelation = scenarioRelationMapper.selectById(scenarioRelation.getId());
            return new BaseResultDto<>(true, scenarioRelationConverter.po2Vo(scenarioRelation));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchAddScenarioRelation(Integer createUserId, List<ScenarioRelationAddDto> scenarioRelationAddDtoList) {
        if (scenarioRelationAddDtoList.isEmpty()) {
            // selectBatchIds如果传入空的List会报错
            return new BaseResultDto<>(true, null);
        }
        try {
            List<ScenarioRelation> scenarioRelationList = scenarioRelationConverter.dtoList2PoList(scenarioRelationAddDtoList);
            for (ScenarioRelation scenarioRelation : scenarioRelationList) {
                scenarioRelation.setCreateUserId(createUserId);
            }
            saveBatch(scenarioRelationList);
            List<Integer> idList = scenarioRelationList.stream().map(ScenarioRelation::getId).collect(Collectors.toList());
            return new BaseResultDto<>(true, scenarioRelationConverter.poList2VoList(scenarioRelationMapper.selectBatchIds(idList)));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto editScenarioRelation(Integer modifyUserId, ScenarioRelationEditDto scenarioRelationEditDto) {
        try {
            ScenarioRelation scenarioRelation = this.getById(scenarioRelationEditDto.getId());
            BeanUtils.copyProperties(scenarioRelationEditDto, scenarioRelation, "id");
            scenarioRelation.setModifyUserId(modifyUserId);
            scenarioRelationMapper.updateById(scenarioRelation);
            return new BaseResultDto<>(true, scenarioRelationConverter.po2Vo(scenarioRelation));
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchEditScenarioRelation(Integer modifyUserId, List<ScenarioRelationEditDto> scenarioRelationEditDtoList) {
        Boolean flag = true;
        List<ScenarioRelationVo> scenarioRelationVoList = new ArrayList<>();
        for (ScenarioRelationEditDto scenarioRelationEditDto : scenarioRelationEditDtoList) {
            BaseResultDto editResultDto = editScenarioRelation(modifyUserId, scenarioRelationEditDto);
            if (!editResultDto.getStatus()) {
                flag = false;
                scenarioRelationVoList.clear();
                break;
            } else {
                scenarioRelationVoList.add((ScenarioRelationVo) editResultDto.getResult());
            }
        }
        return new BaseResultDto<>(flag, scenarioRelationVoList);
    }

    @Override
    public BaseResultDto delScenarioRelation(Integer id) {
        try {
            ScenarioRelation scenarioRelation = this.getById(id);
            scenarioRelation.setIsDeleted(true);
            scenarioRelationMapper.updateById(scenarioRelation);
            return new BaseResultDto<>(true, true);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto batchDelScenarioRelation(List<Integer> idList) {
        Boolean flag = true;
        for (Integer id : idList) {
            if (!delScenarioRelation(id).getStatus()) {
                flag = false;
                break;
            }
        }
        return new BaseResultDto<>(flag, flag);
    }
}

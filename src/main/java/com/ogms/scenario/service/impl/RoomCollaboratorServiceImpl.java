package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.converter.RoomCollaboratorConverter;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomDelCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomEditCollaboratorDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.domain.entity.RoomCollaborator;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.domain.vo.scenario.ScenarioInvolvedVo;
import com.ogms.scenario.mapper.RoomCollaboratorMapper;
import com.ogms.scenario.mapper.RoomMapper;
import com.ogms.scenario.mapper.ScenarioMapper;
import com.ogms.scenario.service.IRoomCollaboratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @name: RoomCollaboratorServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 10:06 AM
 * @version: 1.0
 */
@Service
public class RoomCollaboratorServiceImpl extends ServiceImpl<RoomCollaboratorMapper, RoomCollaborator> implements IRoomCollaboratorService {

    @Autowired
    private RoomCollaboratorMapper roomCollaboratorMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ScenarioMapper scenarioMapper;

    @Resource
    private RoomCollaboratorConverter roomCollaboratorConverter;

    @Override
    public List<RoomCollaborator> getCollaboratorByRoomId(Integer roomId) {
        QueryWrapper<RoomCollaborator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("room_id", roomId);
        List<RoomCollaborator> roomCollaboratorList = roomCollaboratorMapper.selectList(queryWrapper);
        return roomCollaboratorList;
    }

    @Override
    public BaseResultDto addRoomCollaborator(Integer createUserId, RoomAddCollaboratorDto roomAddCollaboratorDto) {
        QueryWrapper<RoomCollaborator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("room_id", roomAddCollaboratorDto.getRoomId())
                .eq("user_id", roomAddCollaboratorDto.getUserId());
        List<RoomCollaborator> roomCollaborators = roomCollaboratorMapper.selectList(queryWrapper);
        if (!roomCollaborators.isEmpty())
            return new BaseResultDto<>(false, "Already exists");
        RoomCollaborator roomCollaborator = roomCollaboratorConverter.dto2Po(roomAddCollaboratorDto);
        try {
            roomCollaboratorMapper.insert(roomCollaborator);
            return new BaseResultDto<>(true, roomCollaborator);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto editRoomCollaborator(Integer modifyUserId, RoomEditCollaboratorDto roomEditCollaboratorDto) {
        try {
            RoomCollaborator roomCollaborator = this.getById(roomEditCollaboratorDto.getId());
            BeanUtils.copyProperties(roomEditCollaboratorDto, roomCollaborator, "id");
            roomCollaborator.setModifyUserId(modifyUserId);
            roomCollaboratorMapper.updateById(roomCollaborator);
            return new BaseResultDto<>(true, roomCollaborator);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto delRoomCollaborator(Integer modifyUserId, RoomDelCollaboratorDto roomDelCollaboratorDto) {
        QueryWrapper<RoomCollaborator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("room_id", roomDelCollaboratorDto.getRoomId())
                .eq("user_id", roomDelCollaboratorDto.getUserId());
        List<RoomCollaborator> roomCollaboratorList = roomCollaboratorMapper.selectList(queryWrapper);
        if (roomCollaboratorList.isEmpty())
            return new BaseResultDto<>(false, "Already deleted");
        RoomCollaborator roomCollaborator = roomCollaboratorList.get(0);
        roomCollaborator.setModifyUserId(modifyUserId);
        roomCollaborator.setIsDeleted(true);
        roomCollaboratorMapper.updateById(roomCollaborator);
        return new BaseResultDto<>(true, roomCollaborator);
    }

    @Override
    public PaginationResultVo getScenarioInvolvedIn(Integer createUserId, BaseQueryDto query) {
        // Find the scenario that the current user is involved in.
        List<Integer> scenarioIdList = new ArrayList<>();
        QueryWrapper<RoomCollaborator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("user_id", createUserId);
        List<RoomCollaborator> roomCollaborators = roomCollaboratorMapper.selectList(queryWrapper);
        for (RoomCollaborator roomCollaborator : roomCollaborators) {
            scenarioIdList.add(roomMapper.selectById(roomCollaborator.getRoomId()).getScenarioId());
        }
        Page<ScenarioInvolvedVo> page = new Page<>(query.getPage(), query.getPageSize());
        scenarioMapper.selectPageByIds(page, scenarioIdList);
        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }
}

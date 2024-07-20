package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.converter.RoomConverter;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.dto.room.RoomEditDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.mapper.RoomMapper;
import com.ogms.scenario.service.IRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @name: RoomServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:49 PM
 * @version: 1.0
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Resource
    private RoomConverter roomConverter;

    @Override
    public Room getRoomByScenarioId(int scenarioId) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("scenario_id", scenarioId);
        List<Room> roomList = roomMapper.selectList(queryWrapper);
        if (roomList.isEmpty())
            return null;
        else
            return roomList.get(0);
    }

    @Override
    public Room getRoomByUUID(String uuid) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("uuid", uuid);
        List<Room> roomList = roomMapper.selectList(queryWrapper);
        if (roomList.isEmpty())
            return null;
        else
            return roomList.get(0);
    }

    @Override
    public BaseResultDto addRoom(Integer createUserId, RoomAddDto roomAddDto) {
        try {
            Room room = roomConverter.dto2Po(roomAddDto);
            room.setCreateUserId(createUserId);
            roomMapper.insert(room);
            room = roomMapper.selectById(room.getId());
            return new BaseResultDto<>(true, room);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto addRoomCollaborator(Integer modifyUserId, RoomAddCollaboratorDto roomAddCollaboratorDto) {
        Room room = getRoomByUUID(roomAddCollaboratorDto.getUuid());
        if (room == null) {
            return new BaseResultDto<>(true, null);
        }
        String newCollaborators = new String();
        for (Integer newId : roomAddCollaboratorDto.getCollaboratorList()) {
            newCollaborators += newId + ",";
        }
        room.setCollaborator(room.getCollaborator() + newCollaborators);
        room.setModifyUserId(modifyUserId);
        room.setModifyDate(roomAddCollaboratorDto.getModifyDate());
        try {
            roomMapper.updateById(room);
            return new BaseResultDto<>(true, room);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto editRoom(Integer modifyUserId, RoomEditDto roomEditDto) {
        try {
            Room room = this.getById(roomEditDto.getId());
            BeanUtils.copyProperties(roomEditDto, room, "id");
            room.setModifyUserId(modifyUserId);
            roomMapper.updateById(room);
            return new BaseResultDto<>(true, room);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto delRoom(Integer id) {
        try {
            Room room = this.getById(id);
            room.setIsDeleted(true);
            roomMapper.updateById(room);
            return new BaseResultDto<>(true, true);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }
}

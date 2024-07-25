package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.dto.room.RoomDelCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomEditDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.domain.vo.PaginationResultVo;

/**
 * @name: IRoomService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:45 PM
 * @version: 1.0
 */
public interface IRoomService extends IService<Room> {

    Room getRoomByScenarioId(int scenarioId);

    Room getRoomByUUID(String uuid);

    BaseResultDto addRoom(Integer createUserId, RoomAddDto roomAddDto);

    BaseResultDto addRoomCollaborator(Integer modifyUserId, RoomAddCollaboratorDto roomAddCollaboratorDto);

    BaseResultDto delRoomCollaborator(Integer modifyUserId, RoomDelCollaboratorDto roomDelCollaboratorDto);

    PaginationResultVo getScenarioInvolvedIn(Integer createUserId, BaseQueryDto query);

    BaseResultDto editRoom(Integer modifyUserId, RoomEditDto roomEditDto);

    BaseResultDto delRoom(Integer id);
}

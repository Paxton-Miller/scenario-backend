package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomDelCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomEditCollaboratorDto;
import com.ogms.scenario.domain.entity.RoomCollaborator;
import com.ogms.scenario.domain.vo.PaginationResultVo;

import java.util.List;

/**
 * @name: IRoomCollaboratorService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 10:03 AM
 * @version: 1.0
 */
public interface IRoomCollaboratorService extends IService<RoomCollaborator> {

    List<RoomCollaborator> getCollaboratorByRoomId(Integer roomId);

    BaseResultDto addRoomCollaborator(Integer createUserId, RoomAddCollaboratorDto roomAddCollaboratorDto);

    BaseResultDto editRoomCollaborator(Integer modifyUserId, RoomEditCollaboratorDto roomEditCollaboratorDto);

    BaseResultDto delRoomCollaborator(Integer modifyUserId, RoomDelCollaboratorDto roomDelCollaboratorDto);

    PaginationResultVo getScenarioInvolvedIn(Integer createUserId, BaseQueryDto query);
}

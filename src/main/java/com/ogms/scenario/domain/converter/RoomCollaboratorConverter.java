package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.domain.entity.RoomCollaborator;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @name: RoomCollaboratorConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 10:43 AM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface RoomCollaboratorConverter {
    RoomCollaborator dto2Po(RoomAddCollaboratorDto roomAddCollaboratorDto);

    List<RoomCollaborator> dtoList2PoList(List<RoomAddCollaboratorDto> roomAddCollaboratorDtos);
}

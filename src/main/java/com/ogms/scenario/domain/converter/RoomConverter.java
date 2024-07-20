package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @name: RoomConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:25 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface RoomConverter {
    Room dto2Po(RoomAddDto roomAddDto);

    List<Room> dtoList2PoList(List<RoomAddDto> roomAddDtos);
}

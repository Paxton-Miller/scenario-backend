package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.map.MapAddDto;
import com.ogms.scenario.domain.entity.Map;
import org.mapstruct.Mapper;

/**
 * @name: MapConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:27 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface MapConverter {
    Map dto2Po(MapAddDto mapAddDto);
}

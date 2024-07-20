package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.resource.ResourceAddDto;
import com.ogms.scenario.domain.entity.Resource;
import org.mapstruct.Mapper;

/**
 * @name: ResourceConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 9:26 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ResourceConverter {
    Resource dto2Po(ResourceAddDto resourceAddDto);
}

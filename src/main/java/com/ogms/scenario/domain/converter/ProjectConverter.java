package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.project.ProjectAddDto;
import com.ogms.scenario.domain.dto.user.UserAddDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.entity.User;
import org.mapstruct.Mapper;

/**
 * @name: ProjectConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/10/2024 6:45 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ProjectConverter {
    Project dto2Po(ProjectAddDto projectAddDto);
}

package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.project.ProjectAddDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.entity.User;
import com.ogms.scenario.domain.vo.PaginationResultVo;

/**
 * @name: IProjectService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/10/2024 2:17 PM
 * @version: 1.0
 */
public interface IProjectService extends IService<Project> {
    PaginationResultVo getAllProject(BaseQueryDto query);

    BaseResultDto addProject(Integer createUserId, ProjectAddDto projectAddDto);
}

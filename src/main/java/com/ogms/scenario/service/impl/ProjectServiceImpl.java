package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.converter.ProjectConverter;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.project.ProjectAddDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.mapper.ProjectMapper;
import com.ogms.scenario.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @name: ProjectServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/10/2024 2:17 PM
 * @version: 1.0
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Resource
    private ProjectConverter projectConverter;

    @Override
    public PaginationResultVo getAllProject(BaseQueryDto query) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        Page<Project> page = new Page<>(query.getPage(), query.getPageSize());
        projectMapper.selectPage(page, queryWrapper);

        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    @Override
    public PaginationResultVo getAllMyOwnProject(Integer userId, BaseQueryDto query) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("create_user_id", userId);
        Page<Project> page = new Page<>(query.getPage(), query.getPageSize());
        projectMapper.selectPage(page, queryWrapper);

        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    @Override
    public BaseResultDto addProject(Integer createUserId, ProjectAddDto projectAddDto) {
        try {
            Project project = projectConverter.dto2Po(projectAddDto);
            project.setCreateUserId(createUserId);
            projectMapper.insert(project);
            project = projectMapper.selectById(project.getId());
            return new BaseResultDto<>(true, project);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }
}

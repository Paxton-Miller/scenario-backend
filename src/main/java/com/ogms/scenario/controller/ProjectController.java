package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.project.ProjectAddDto;
import com.ogms.scenario.domain.dto.user.UserAddDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.entity.User;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.service.IProjectService;
import com.ogms.scenario.service.IUserService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: ProjectController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/10/2024 2:16 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/project")
@Api(value = "Project API", tags = {"Project"})
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @ApiOperation("获取所有项目数据")
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin')")
    public R<PaginationResultVo> getAllProject(@ModelAttribute BaseQueryDto query) {
        return R.success(projectService.getAllProject(query), "获取成功");
    }

    @ApiOperation("通过id获取项目数据")
    @GetMapping("/getById")
    @PreAuthorize("hasAuthority('admin')")
    public R<Project> getProjectById(Integer id) {
        return R.success(projectService.getById(id), "获取成功");
    }

    @ApiOperation("项目添加")
    @PostMapping("/add")
    public R addProject(HttpServletRequest request, @RequestBody ProjectAddDto projectAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto baseAddResultDto;
            baseAddResultDto = projectService.addProject(createUserId, projectAddDto);
            if (baseAddResultDto.getStatus()) {
                return R.success(baseAddResultDto.getResult());
            } else {
                return R.fail(baseAddResultDto.getResult().toString().trim());
            }
        }
    }
}

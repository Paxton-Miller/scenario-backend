package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.resource.ResourceAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.service.IResourceService;
import com.ogms.scenario.service.IScenarioService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: ResourceController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 7:57 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/resource")
@Api(value = "Resource API", tags = {"Resource"})
public class ResourceController {
    @Autowired
    private IResourceService resourceService;

    @ApiOperation("获取所有私人资源")
    @GetMapping("getAllByUserId")
    @PreAuthorize("hasAnyAuthority('admin')")
    public R getAllResourceByUserId(HttpServletRequest request, BaseQueryDto query) {
        Integer createUserId = null;
        createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.success(resourceService.getAllResourceByUserId(createUserId, query));
    }

    @ApiOperation("获取所有公共资源")
    @GetMapping("getAllOpen")
    @PreAuthorize("hasAnyAuthority('admin')")
    public R getAllOpenResource(BaseQueryDto query) {
        return R.success(resourceService.getAllOpenResource(query));
    }

    @ApiOperation("上传资源")
    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('admin')")
    public R uploadResource(String uuidName, @RequestParam("file") MultipartFile file) {
        BaseResultDto uploadResultDto = resourceService.uploadResource(uuidName, file);
        if (uploadResultDto.getStatus()) {
            return R.success(uploadResultDto.getResult());
        } else {
            return R.fail(uploadResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("添加资源")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addResource(HttpServletRequest request, @RequestBody ResourceAddDto resourceAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto addResultDto;
            addResultDto = resourceService.addResource(createUserId, resourceAddDto);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
        }
    }
}

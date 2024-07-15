package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationAddDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationEditDto;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.domain.vo.scenarioRelation.ScenarioRelationVo;
import com.ogms.scenario.service.IScenarioRelationService;
import com.ogms.scenario.service.IScenarioService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @name: ScenarioRelationController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 8:37 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/scenarioRelation")
@Api(value = "ScenarioRelation API", tags = {"ScenarioRelation"})
public class ScenarioRelationController {
    @Autowired
    private IScenarioRelationService scenarioRelationService;

    @ApiOperation("获取所有场景关系数据")
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin')")
    public R<List<ScenarioRelationVo>> getAllScenarioRelation() {
        return R.success(scenarioRelationService.getAllScenarioRelation(), "获取成功");
    }

    @ApiOperation("获取某项目下的所有场景关系数据")
    @GetMapping("/getAllByProjectId")
    @PreAuthorize("hasAuthority('admin')")
    public R<List<ScenarioRelationVo>> getAllScenarioRelationByProjectId(Integer id) {
        return R.success(scenarioRelationService.getAllScenarioRelationByProjectId(id), "获取成功");
    }

    @ApiOperation("增加一个场景关系")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addScenarioRelation(HttpServletRequest request, @RequestBody ScenarioRelationAddDto scenarioRelationAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto addResultDto;
            addResultDto = scenarioRelationService.addScenarioRelation(createUserId, scenarioRelationAddDto);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("增加多个场景关系")
    @PostMapping("/batchAdd")
    @PreAuthorize("hasAuthority('admin')")
    public R batchAddScenarioRelation(HttpServletRequest request, @RequestBody List<ScenarioRelationAddDto> scenarioRelationAddDtoList) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto addResultDto;
            addResultDto = scenarioRelationService.batchAddScenarioRelation(createUserId, scenarioRelationAddDtoList);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("修改场景关系")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editScenarioRelation(HttpServletRequest request, @RequestBody ScenarioRelationEditDto scenarioRelationEditDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        BaseResultDto editResultDto = scenarioRelationService.editScenarioRelation(modifyUserId, scenarioRelationEditDto);
        if (editResultDto.getStatus()) {
            return R.success(editResultDto.getResult());
        } else {
            return R.fail(editResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("批量编辑场景关系数据")
    @PostMapping("/batchEdit")
    @PreAuthorize("hasAuthority('admin')")
    public R batchEditScenarioRelation(HttpServletRequest request, @RequestBody List<ScenarioRelationEditDto> scenarioRelationEditDtoList) {
        Integer modifyUserId = null;
        try {
            modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto batchEditResultDto;
            batchEditResultDto = scenarioRelationService.batchEditScenarioRelation(modifyUserId, scenarioRelationEditDtoList);
            if (batchEditResultDto.getStatus()) {
                return R.success( batchEditResultDto.getResult());
            } else {
                return R.fail(batchEditResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("删除场景关系")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delScenarioRelation(@RequestParam Integer id) {
        BaseResultDto<Boolean> delResultDto = scenarioRelationService.delScenarioRelation(id);
        if (delResultDto.getStatus()) {
            return R.success(delResultDto.getResult());
        } else {
            return R.fail();
        }
    }

    @ApiOperation("批量删除场景关系")
    @DeleteMapping("/batchDel")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> batchDelScenarioRelation(@RequestBody @ApiParam(value = "条目id数组", required = true) List<Integer> idList) {
        BaseResultDto<Boolean> batchDelResultDto = scenarioRelationService.batchDelScenarioRelation(idList);
        if (batchDelResultDto.getStatus()) {
            return R.success(batchDelResultDto.getResult());
        } else {
            return R.fail();
        }
    }
}

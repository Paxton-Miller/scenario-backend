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
            Integer finalCreateUserId = createUserId;
            return R.handleService(() -> scenarioRelationService.addScenarioRelation(finalCreateUserId, scenarioRelationAddDto));
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
            Integer finalCreateUserId = createUserId;
            return R.handleService(() -> scenarioRelationService.batchAddScenarioRelation(finalCreateUserId, scenarioRelationAddDtoList));
        }
    }

    @ApiOperation("修改场景关系")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editScenarioRelation(HttpServletRequest request, @RequestBody ScenarioRelationEditDto scenarioRelationEditDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.handleService(() -> scenarioRelationService.editScenarioRelation(modifyUserId, scenarioRelationEditDto));
    }

    @ApiOperation("批量编辑场景关系数据")
    @PostMapping("/batchEdit")
    @PreAuthorize("hasAuthority('admin')")
    public R batchEditScenarioRelation(HttpServletRequest request, @RequestBody List<ScenarioRelationEditDto> scenarioRelationEditDtoList) {
        Integer modifyUserId = null;
        try {
            modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            Integer finalModifyUserId = modifyUserId;
            return R.handleService(() -> scenarioRelationService.batchEditScenarioRelation(finalModifyUserId, scenarioRelationEditDtoList));
        }
    }

    @ApiOperation("删除场景关系")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delScenarioRelation(@RequestParam Integer id) {
        return R.handleService(() -> scenarioRelationService.delScenarioRelation(id));
    }

    @ApiOperation("批量删除场景关系")
    @DeleteMapping("/batchDel")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> batchDelScenarioRelation(@RequestBody @ApiParam(value = "条目id数组", required = true) List<Integer> idList) {
        return R.handleService(() -> scenarioRelationService.batchDelScenarioRelation(idList));
    }
}

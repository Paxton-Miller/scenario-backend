package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.enums.DimensionType;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.service.IScenarioService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @name: ScenarioController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:19 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/scenario")
@Api(value = "Scenario API", tags = {"Scenario"})
public class ScenarioController {
    @Autowired
    private IScenarioService scenarioService;

    @ApiOperation("通过id获取场景数据")
    @GetMapping("/getById")
    @PreAuthorize("hasAuthority('admin')")
    public R<Scenario> getScenarioById(Integer id) {
        return R.success(scenarioService.getById(id), "获取成功");
    }

    @ApiOperation("获取所有场景数据")
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin')")
    public R<List<ScenarioVo>> getAllScenario() {
        return R.success(scenarioService.getAllScenario(), "获取成功");
    }

    @ApiOperation("获取某项目下的所有场景数据")
    @GetMapping("/getAllByProjectId")
    @PreAuthorize("hasAuthority('admin')")
    public R<List<ScenarioVo>> getAllScenarioByProjectId(Integer id) {
        return R.success(scenarioService.getAllScenarioByProjectId(id), "获取成功");
    }

    @ApiOperation("存储graph对应的json文件")
    @PostMapping("/saveGraphJsonByIdAndType")
    @PreAuthorize("hasAuthority('admin')")
    public R saveScenarioGraphJsonByIdAndType(Integer id, DimensionType type, @RequestBody String graph) {
//        JSON.toJSONString()
        return R.handleService(() -> scenarioService.saveScenarioGraphJsonByIdAndType(id, type.toString(), graph));
    }

    @ApiOperation("返回graph对应的json文件")
    @GetMapping("/getGraphJsonByIdAndType")
    @PreAuthorize("hasAuthority('admin')")
    public R getScenarioGraphJsonByIdAndType(Integer id, DimensionType type) {
//        JSON.toJSONString()
        return R.handleService(() -> scenarioService.getScenarioGraphJsonByIdAndType(id, type.toString()));
    }

    @ApiOperation("增加一个场景")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addScenario(HttpServletRequest request, @RequestBody ScenarioAddDto scenarioAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            Integer finalCreateUserId = createUserId;
            return R.handleService(() -> scenarioService.addScenario(finalCreateUserId, scenarioAddDto));
        }
    }

    @ApiOperation("增加多个场景")
    @PostMapping("/batchAdd")
    @PreAuthorize("hasAuthority('admin')")
    public R batchAddScenario(HttpServletRequest request, @RequestBody List<ScenarioAddDto> scenarioAddDtoList) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            Integer finalCreateUserId = createUserId;
            return R.handleService(() -> scenarioService.batchAddScenario(finalCreateUserId, scenarioAddDtoList));
        }
    }

    @ApiOperation("修改场景")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editScenario(HttpServletRequest request, @RequestBody ScenarioEditDto scenarioEditDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.handleService(() -> scenarioService.editScenario(modifyUserId, scenarioEditDto));
    }

    @ApiOperation("批量编辑场景数据")
    @PostMapping("/batchEdit")
    @PreAuthorize("hasAuthority('admin')")
    public R batchEditScenario(HttpServletRequest request, @RequestBody List<ScenarioEditDto> scenarioEditDtoList) {
        Integer modifyUserId = null;
        try {
            modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            Integer finalModifyUserId = modifyUserId;
            return R.handleService(() -> scenarioService.batchEditScenario(finalModifyUserId, scenarioEditDtoList));
        }
    }

    @ApiOperation("删除场景")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delScenario(@RequestParam Integer id) {
        return R.handleService(() -> scenarioService.delScenario(id));
    }

    @ApiOperation("批量删除场景")
    @DeleteMapping("/batchDel")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> batchDelScenario(@RequestBody @ApiParam(value = "条目id数组", required = true) List<Integer> idList) {
        return R.handleService(() -> scenarioService.batchDelScenario(idList));
    }
}

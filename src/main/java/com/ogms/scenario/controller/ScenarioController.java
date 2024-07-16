package com.ogms.scenario.controller;

import com.alibaba.fastjson.JSON;
import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.service.IScenarioService;
import com.ogms.scenario.service.IUserService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
        System.out.println(Constants.ROOT_PATH);
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
    @PostMapping("/saveGraphJsonById")
    @PreAuthorize("hasAuthority('admin')")
    public R saveScenarioGraphJsonById(Integer id, @RequestBody String graph) {
//        JSON.toJSONString()
        BaseResultDto saveResultDto = scenarioService.saveScenarioGraphJsonById(id, graph);
        if (saveResultDto.getStatus()) {
            return R.success(saveResultDto.getResult());
        } else {
            return R.fail(saveResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("返回graph对应的json文件")
    @GetMapping("/getGraphJsonById")
    @PreAuthorize("hasAuthority('admin')")
    public R getScenarioGraphJsonById(Integer id) {
//        JSON.toJSONString()
        BaseResultDto getResultDto = scenarioService.getScenarioGraphJsonById(id);
        if (getResultDto.getStatus()) {
            return R.success(getResultDto.getResult());
        } else {
            return R.fail(getResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("增加一个场景")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addScenario(HttpServletRequest request, @RequestBody ScenarioAddDto scenarioAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto addResultDto;
            addResultDto = scenarioService.addScenario(createUserId, scenarioAddDto);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
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
            BaseResultDto addResultDto;
            addResultDto = scenarioService.batchAddScenario(createUserId, scenarioAddDtoList);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("修改场景")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editScenario(HttpServletRequest request, @RequestBody ScenarioEditDto scenarioEditDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        BaseResultDto editResultDto = scenarioService.editScenario(modifyUserId, scenarioEditDto);
        if (editResultDto.getStatus()) {
            return R.success(editResultDto.getResult());
        } else {
            return R.fail(editResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("批量编辑场景数据")
    @PostMapping("/batchEdit")
    @PreAuthorize("hasAuthority('admin')")
    public R batchEditScenario(HttpServletRequest request, @RequestBody List<ScenarioEditDto> scenarioEditDtoList) {
        Integer modifyUserId = null;
        try {
            modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto batchEditResultDto;
            batchEditResultDto = scenarioService.batchEditScenario(modifyUserId, scenarioEditDtoList);
            if (batchEditResultDto.getStatus()) {
                return R.success(batchEditResultDto.getResult());
            } else {
                return R.fail(batchEditResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("删除场景")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delScenario(@RequestParam Integer id) {
        BaseResultDto<Boolean> delResultDto = scenarioService.delScenario(id);
        if (delResultDto.getStatus()) {
            return R.success(delResultDto.getResult());
        } else {
            return R.fail();
        }
    }

    @ApiOperation("批量删除场景")
    @DeleteMapping("/batchDel")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> batchDelScenario(@RequestBody @ApiParam(value = "条目id数组", required = true) List<Integer> idList) {
        BaseResultDto<Boolean> batchDelResultDto = scenarioService.batchDelScenario(idList);
        if (batchDelResultDto.getStatus()) {
            return R.success(batchDelResultDto.getResult());
        } else {
            return R.fail();
        }
    }
}

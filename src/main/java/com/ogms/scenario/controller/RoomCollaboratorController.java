package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomDelCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomEditCollaboratorDto;
import com.ogms.scenario.service.IRoomCollaboratorService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: RoomCollaboratorController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 10:14 AM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/roomCollaborator")
@Api(value = "RoomCollaborator API", tags = {"RoomCollaborator"})
public class RoomCollaboratorController {
    @Autowired
    private IRoomCollaboratorService roomCollaboratorService;

    @ApiOperation("得到协作人员")
    @GetMapping("/getByRoomId")
    @PreAuthorize("hasAuthority('admin')")
    public R getCollaboratorByRoomId(Integer roomId) {
        return R.success(roomCollaboratorService.getCollaboratorByRoomId(roomId));
    }

    @ApiOperation("增加协作人员")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addRoomCollaborator(HttpServletRequest request, @RequestBody RoomAddCollaboratorDto roomAddCollaboratorDto) {
        Integer createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.handleService(() -> roomCollaboratorService.addRoomCollaborator(createUserId, roomAddCollaboratorDto));
    }

    @ApiOperation("编辑协作人员权限")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editRoomCollaborator(HttpServletRequest request, @RequestBody RoomEditCollaboratorDto roomEditCollaboratorDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.handleService(() -> roomCollaboratorService.editRoomCollaborator(modifyUserId, roomEditCollaboratorDto));
    }

    @ApiOperation("删除协作人员")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R delRoomCollaborator(HttpServletRequest request, @RequestBody RoomDelCollaboratorDto roomDelCollaboratorDto) {
        Integer createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.handleService(() -> roomCollaboratorService.delRoomCollaborator(createUserId, roomDelCollaboratorDto));
    }

    @ApiOperation("获取协作人员所参与的场景")
    @GetMapping("/getScenarioInvolvedIn")
    @PreAuthorize("hasAuthority('admin')")
    public R getScenarioInvolvedIn(HttpServletRequest request, BaseQueryDto query) {
        Integer createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.success(roomCollaboratorService.getScenarioInvolvedIn(createUserId, query));
    }
}

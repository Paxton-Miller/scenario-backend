package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.room.RoomAddCollaboratorDto;
import com.ogms.scenario.domain.dto.room.RoomAddDto;
import com.ogms.scenario.domain.dto.room.RoomEditDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.service.IRoomService;
import com.ogms.scenario.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @name: RoomController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:14 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/room")
@Api(value = "Room API", tags = {"Room"})
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @ApiOperation("通过id获取协作数据")
    @GetMapping("/getById")
    @PreAuthorize("hasAuthority('admin')")
    public R<Room> getRoomById(Integer id) {
        return R.success(roomService.getById(id), "获取成功");
    }

    @ApiOperation("获取某场景下的协作数据")
    @GetMapping("/getByScenarioId")
    @PreAuthorize("hasAuthority('admin')")
    public R<Room> getRoomByScenarioId(Integer id) {
        return R.success(roomService.getRoomByScenarioId(id), "获取成功");
    }

    @ApiOperation("获取匹配UUID的协作数据")
    @GetMapping("/getByUUID")
    @PreAuthorize("hasAuthority('admin')")
    public R<Room> getRoomByUUId(String uuid) {
        return R.success(roomService.getRoomByUUID(uuid), "获取成功");
    }

    @ApiOperation("增加一个协作室")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addRoom(HttpServletRequest request, @RequestBody RoomAddDto roomAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            BaseResultDto addResultDto;
            addResultDto = roomService.addRoom(createUserId, roomAddDto);
            if (addResultDto.getStatus()) {
                return R.success(addResultDto.getResult());
            } else {
                return R.fail(addResultDto.getResult().toString().trim());
            }
        }
    }

    @ApiOperation("增加协作人员")
    @PostMapping("/addCollaborator")
    @PreAuthorize("hasAuthority('admin')")
    public R addRoomCollaborator(HttpServletRequest request, @RequestBody RoomAddCollaboratorDto roomAddCollaboratorDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        BaseResultDto addResultDto;
        addResultDto = roomService.addRoomCollaborator(modifyUserId, roomAddCollaboratorDto);
        if (addResultDto.getStatus()) {
            return R.success(addResultDto.getResult());
        } else {
            return R.fail(addResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("修改协作室")
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('admin')")
    public R editRoom(HttpServletRequest request, @RequestBody RoomEditDto roomEditDto) {
        Integer modifyUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        BaseResultDto editResultDto = roomService.editRoom(modifyUserId, roomEditDto);
        if (editResultDto.getStatus()) {
            return R.success(editResultDto.getResult());
        } else {
            return R.fail(editResultDto.getResult().toString().trim());
        }
    }

    @ApiOperation("删除协作室")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delRoom(@RequestParam Integer id) {
        BaseResultDto<Boolean> delResultDto = roomService.delRoom(id);
        if (delResultDto.getStatus()) {
            return R.success(delResultDto.getResult());
        } else {
            return R.fail();
        }
    }
}

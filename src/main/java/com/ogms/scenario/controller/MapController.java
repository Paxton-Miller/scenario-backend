package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.map.MapAddDto;
import com.ogms.scenario.service.IMapService;
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
 * @name: MapController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:27 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/map")
@Api(value = "Map API", tags = {"Map"})
public class MapController {

    @Autowired
    private IMapService mapService;

    @ApiOperation("获取所有私人地图")
    @GetMapping("getAllByUserId")
    @PreAuthorize("hasAnyAuthority('admin')")
    public R getAllMapByUserId(HttpServletRequest request, BaseQueryDto query) {
        Integer createUserId = null;
        createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        return R.success(mapService.getAllMapByUserId(createUserId, query));
    }

    @ApiOperation("获取所有公共地图")
    @GetMapping("getAllOpen")
    @PreAuthorize("hasAnyAuthority('admin')")
    public R getAllOpenMap(BaseQueryDto query) {
        return R.success(mapService.getAllOpenMap(query));
    }

    @ApiOperation("添加地图")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addMap(HttpServletRequest request, @RequestBody MapAddDto mapAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            Integer finalCreateUserId = createUserId;
            return R.handleService(() -> mapService.add(finalCreateUserId, mapAddDto));
        }
    }

    @ApiOperation("删除地图")
    @DeleteMapping("/del")
    @PreAuthorize("hasAuthority('admin')")
    public R<Boolean> delMap(@RequestParam Integer id) {
        return R.handleService(() -> mapService.delById(id));
    }
}

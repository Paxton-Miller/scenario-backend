package com.ogms.scenario.controller;

import com.ogms.scenario.common.R;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.service.IGeoJsonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * @name: GeoJsonController
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 3:06 PM
 * @version: 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/geojson")
@Api(value = "GeoJson API", tags = {"GeoJson"})
public class GeoJsonController {

    @Autowired
    private IGeoJsonService geoJsonService;

    @ApiOperation("保存geojson数据")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public R addGeoJson(@RequestBody Map<String, Object> geoJsonContent) {
        return R.handleService(() -> geoJsonService.addGeoJson(geoJsonContent));
    }

    @ApiOperation("保存geojson数据")
    @PostMapping("/addByFile")
    @PreAuthorize("hasAuthority('admin')")
    public R addGeoJsonByFile(@RequestParam("file") MultipartFile file) {
        return R.handleService(() -> geoJsonService.addGeoJsonByFile(file));
    }

    @ApiOperation("根据id得到geojson数据")
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public Object getGeoJsonById(@PathVariable Serializable id) {
        return geoJsonService.getGeoJsonById(id);
    }
}

package com.ogms.scenario.service.impl;

import com.anwen.mongo.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.entity.GeoJson;
import com.ogms.scenario.mapper.GeoJsonMapper;
import com.ogms.scenario.service.IGeoJsonService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * @name: GeoJsonServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 3:19 PM
 * @version: 1.0
 */
@Service
public class GeoJsonServiceImpl extends ServiceImpl<GeoJson> implements IGeoJsonService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BaseResultDto addGeoJson(Map<String, Object> geoJsonContent) {
        try {
            GeoJson geoJson = new GeoJson();
            geoJson.setData(geoJsonContent);
            save(geoJson);
            return new BaseResultDto<>(true, "http://localhost:8898/geojson/" + geoJson.getId());
        } catch (Exception e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
    }

    @Override
    public BaseResultDto addGeoJsonByFile(MultipartFile file) {
        try {
            Map<String, Object> jsonContent = objectMapper.readValue(file.getBytes(), new TypeReference<Map<String, Object>>() {});
            GeoJson geoJson = new GeoJson();
            geoJson.setData(jsonContent);
            save(geoJson);
            return new BaseResultDto<>(true, "http://localhost:8898/geojson/" + geoJson.getId());
        } catch (IOException e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
    }

    @Override
    public Object getGeoJsonById(Serializable id) {
        GeoJson geoJson = this.getById(id);
        return geoJson.getData();
    }
}

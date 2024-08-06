package com.ogms.scenario.service;

import com.anwen.mongo.service.IService;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.entity.GeoJson;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Map;

/**
 * @name: IGeoJsonService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 3:18 PM
 * @version: 1.0
 */
public interface IGeoJsonService extends IService<GeoJson> {

    BaseResultDto addGeoJson(Map<String, Object> geoJsonContent);

    BaseResultDto addGeoJsonByFile(MultipartFile file);

    Object getGeoJsonById(Serializable id);

}

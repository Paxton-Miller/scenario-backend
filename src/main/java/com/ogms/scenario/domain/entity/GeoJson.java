package com.ogms.scenario.domain.entity;

import com.anwen.mongo.annotation.ID;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * @name: GeoJson
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 2:41 PM
 * @version: 1.0
 */
@Data
@Document(collation = "geojson")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "geojson实体类")
public class GeoJson {
    @ID
    private String id;

    @Field("data")
    private Map<String, Object> data;
}

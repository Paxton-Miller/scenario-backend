package com.ogms.scenario.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @name: GraphJson
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 3:01 PM
 * @version: 1.0
 */
@Data
@Document(value = "graphjson")
@ApiModel(description = "graphjson实体类")
public class GraphJson {
    @Id
    private ObjectId id;

    @Field("data")
    private String data;
}

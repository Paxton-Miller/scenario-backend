package com.ogms.scenario.domain.dto.scenarioRelation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @name: ScenarioRelationAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 8:56 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "场景关系信息添加")
public class ScenarioRelationAddDto {
    @NotBlank
    @JsonProperty("label")
    @ApiModelProperty(required = true)
    private String name;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer projectId;

    @NotNull
    @JsonProperty("source")
    @ApiModelProperty(required = true)
    private Integer sourceId;

    @NotNull
    @JsonProperty("target")
    @ApiModelProperty(required = true)
    private Integer targetId;

    private String description = null;
}

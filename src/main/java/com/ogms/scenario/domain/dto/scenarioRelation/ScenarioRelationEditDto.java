package com.ogms.scenario.domain.dto.scenarioRelation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @name: ScenarioRelationEditDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 8:56 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "数据编辑")
public class ScenarioRelationEditDto {
    private Integer id;

    @JsonProperty("label")
    @ApiModelProperty(required = true)
    private String name = "";

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

    private String description;

    @JsonIgnore // 不暴露给前端，直接默认现在
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate = new Date();
}

package com.ogms.scenario.domain.dto.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @name: ScenarioAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 3:10 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "场景信息添加")
public class ScenarioAddDto {
    @NotBlank
    @JsonProperty("label")
    @ApiModelProperty(required = true)
    private String name;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer projectId;

    @JsonProperty("x")
    private Float graphX = 450F;

    @JsonProperty("y")
    private Float graphY = 260F;

    @JsonProperty("width")
    private Float graphWidth = 80F;

    @JsonProperty("height")
    private Float graphHeight = 40F;

    private String node;

    private String description = null;
}

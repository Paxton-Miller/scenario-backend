package com.ogms.scenario.domain.dto.map;

import com.ogms.scenario.domain.enums.ResourceAccessLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @name: MapAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:24 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "地图信息添加")
public class MapAddDto {

    @NotBlank
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(required = true)
    private String mapUrl;

    @NotBlank
    @ApiModelProperty(required = true)
    private String logoUrl;

    @NotBlank
    @ApiModelProperty(required = true)
    private String vectorUrl;

    @NotNull
    @ApiModelProperty(required = true)
    private String attributions;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceAccessLevel accessLevel;

    private String description = null;
}

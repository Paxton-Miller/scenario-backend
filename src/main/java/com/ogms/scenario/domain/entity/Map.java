package com.ogms.scenario.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import com.ogms.scenario.domain.enums.ResourceAccessLevel;
import com.ogms.scenario.domain.enums.ResourceFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @name: Map
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:21 PM
 * @version: 1.0
 */
@Data
@TableName("map")
@ApiModel(description = "地图实体类")
public class Map extends BaseEntity {
    @Size(max = 255)
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
}

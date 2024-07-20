package com.ogms.scenario.domain.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogms.scenario.domain.enums.ResourceAccessLevel;
import com.ogms.scenario.domain.enums.ResourceFormat;
import com.ogms.scenario.domain.enums.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @name: ResourceAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 9:20 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "资源信息添加")
public class ResourceAddDto {
    @NotBlank
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(required = true)
    private String uuidName;

    @NotBlank
    @ApiModelProperty(required = true)
    private String url;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceFormat format;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceAccessLevel accessLevel;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceType type;

    private String description = null;
}

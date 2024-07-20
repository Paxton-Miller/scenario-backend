package com.ogms.scenario.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.scenario.domain.entity.common.BaseEntity;
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
import javax.validation.constraints.Size;

/**
 * @name: Resource
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 5:45 PM
 * @version: 1.0
 */
@Data
@TableName("resource")
@ApiModel(description = "资源实体类")
public class Resource extends BaseEntity {
    @Size(max = 255)
    @NotBlank
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(required = true)
    private String uuidName;

    @NotBlank
    @ApiModelProperty(required = true)
    private String url;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceFormat format;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceAccessLevel accessLevel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private ResourceType type;
}

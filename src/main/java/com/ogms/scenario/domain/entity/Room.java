package com.ogms.scenario.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @name: Room
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:34 PM
 * @version: 1.0
 */
@Data
@TableName("room")
@ApiModel(description = "协作室实体类")
public class Room extends BaseEntity {
    @NotNull
    @ApiModelProperty(required = true)
    private String collaborator;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer scenarioId;

    private String uuid;
}

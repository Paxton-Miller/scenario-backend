package com.ogms.scenario.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * @name: RoomCollaborator
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 9:59 AM
 * @version: 1.0
 */
@Data
@TableName("room_collaborator")
@ApiModel(description = "房间协作实体类")
public class RoomCollaborator extends BaseEntity {

    @NotNull
    @ApiModelProperty(required = true)
    private Integer roomId;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @TableField("permission_level")
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;
}

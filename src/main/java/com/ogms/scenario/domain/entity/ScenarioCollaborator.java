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
 * @name: ScenarioCollaborator
 * @description: 场景协作实体类
 * @author: Lingkai Shi
 * @date: 7/10/2024 2:04 PM
 * @version: 1.0
 */
@Data
@TableName("scenario_collaborator")
@ApiModel(description = "场景协作实体类")
public class ScenarioCollaborator extends BaseEntity {
    @NotNull
    @ApiModelProperty(required = true)
    private Integer scenario_id;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer user_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @TableField("permission_level")
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;
}

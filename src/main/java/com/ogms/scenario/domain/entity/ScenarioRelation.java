package com.ogms.scenario.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @name: ScenarioRelation
 * @description: 场景关系实体类
 * @author: Lingkai Shi
 * @date: 7/10/2024 1:50 PM
 * @version: 1.0
 */
@Data
@TableName("scenario_relation")
@ApiModel(description = "场景关系实体类")
public class ScenarioRelation extends BaseEntity {
    @Size(max = 255)
    @NotBlank
    @ApiModelProperty(required = true)
    private String name;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer source_id;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer target_id;

    @NotNull
    @ApiModelProperty(example = "true", notes = "1表示普通用户", required = true)
    private Boolean type;
}

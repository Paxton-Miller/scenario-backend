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
 * @name: Project
 * @description: 项目实体类
 * @author: Lingkai Shi
 * @date: 7/10/2024 12:01 PM
 * @version: 1.0
 */
@Data
@TableName("project")
@ApiModel(description = "项目实体类")
public class Project extends BaseEntity {
    @Size(max = 255)
    @NotBlank
    @ApiModelProperty(required = true)
    private String name;

    @NotNull
    @Size(max = 1, min = 0)
    @ApiModelProperty(required = true)
    private Float progress;

    @NotNull
    @ApiModelProperty(example = "true", notes = "1表示普通用户", required = true)
    private Boolean type;

    @NotNull
    @ApiModelProperty(required = true)
    private Boolean isEnable;
}

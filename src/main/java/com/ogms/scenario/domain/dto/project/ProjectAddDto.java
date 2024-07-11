package com.ogms.scenario.domain.dto.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @name: ProjectAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/10/2024 5:30 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "项目信息添加")
public class ProjectAddDto {
    @ApiModelProperty(required = true)
    private String name;

    private String description;
}

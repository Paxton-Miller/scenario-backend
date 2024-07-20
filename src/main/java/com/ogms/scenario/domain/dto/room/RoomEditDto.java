package com.ogms.scenario.domain.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @name: RoomEditDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:32 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "数据编辑")
public class RoomEditDto {
    private Integer id;

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

    private String description;

    @JsonIgnore // 不暴露给前端，直接默认现在
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate = new Date();
}

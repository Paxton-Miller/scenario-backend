package com.ogms.scenario.domain.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @name: RoomEditCollaboratorDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/29/2024 8:15 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "添加协作成员")
public class RoomEditCollaboratorDto {


    @NotNull
    @ApiModelProperty(required = true)
    private Integer id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;

    private String description = null;

    @JsonIgnore // 不暴露给前端，直接默认现在
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate = new Date();

    public RoomEditCollaboratorDto(Integer id, PermissionLevelEnum permissionLevel) {
        this.id = id;
        this.permissionLevel = permissionLevel;
    }
}

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
import java.util.List;

/**
 * @name: RoomAddCollaboratorDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/20/2024 8:57 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "添加协作成员")
public class RoomAddCollaboratorDto {

    @NotNull
    @ApiModelProperty(required = true)
    private Integer roomId;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer userId;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;

    private String description = null;

    public RoomAddCollaboratorDto(Integer roomId, Integer userId, PermissionLevelEnum permissionLevel) {
        this.roomId = roomId;
        this.userId = userId;
        this.permissionLevel = permissionLevel;
    }
}

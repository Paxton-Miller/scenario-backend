package com.ogms.scenario.domain.dto.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogms.scenario.domain.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @name: RoomAddDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 9:27 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "协作室信息添加")
public class RoomAddDto {
    @NotNull
    @ApiModelProperty(required = true)
    private String collaborator;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private PermissionLevelEnum permissionLevel;

    @NotNull
    @JsonIgnore
    private String uuid = UUID.randomUUID().toString();

    @NotNull
    @ApiModelProperty(required = true)
    private Integer scenarioId;

    private String description = null;

    public RoomAddDto(String collaborator, PermissionLevelEnum permissionLevel, Integer scenarioId, String description) {
        this.collaborator = collaborator;
        this.permissionLevel = permissionLevel;
        this.scenarioId = scenarioId;
        this.description = description;
    }
}

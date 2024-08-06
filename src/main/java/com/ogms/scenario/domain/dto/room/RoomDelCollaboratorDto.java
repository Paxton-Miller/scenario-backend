package com.ogms.scenario.domain.dto.room;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @name: RoomDelCollaboratorDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/25/2024 8:08 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "删除协作成员")
public class RoomDelCollaboratorDto {

    private Integer roomId;

    private Integer userId;
}

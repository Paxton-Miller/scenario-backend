package com.ogms.scenario.domain.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(description = "添加协作成员")
public class RoomAddCollaboratorDto {
    private String uuid;

    @NotNull
    @ApiModelProperty(required = true)
    private List<Integer> collaboratorList;

    @JsonIgnore // 不暴露给前端，直接默认现在
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate = new Date();
}

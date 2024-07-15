package com.ogms.scenario.domain.dto.scenario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @name: ScenarioEditDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 4:36 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "数据编辑")
public class ScenarioEditDto {

    private Integer id;

    @NotBlank
    @JsonProperty("label")
    @ApiModelProperty(required = true)
    private String name;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer projectId;

    @JsonProperty("x")
    private Float graphX = 450F;

    @JsonProperty("y")
    private Float graphY = 260F;

    @JsonProperty("width")
    private Float graphWidth = 80F;

    @JsonProperty("height")
    private Float graphHeight = 40F;

    private String description;

    @JsonIgnore // 不暴露给前端，直接默认现在
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate = new Date();
}

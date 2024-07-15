package com.ogms.scenario.domain.vo.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @name: ScenarioVo
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 4:01 PM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@ApiModel(description = "返回的节点数据形式")
public class ScenarioVo extends BaseEntity {

    @JsonProperty("label")
    private String name;

    private Integer projectId;

    @JsonProperty("x")
    private Integer graphX;

    @JsonProperty("y")
    private Integer graphY;

    @JsonProperty("width")
    private Integer graphWidth;

    @JsonProperty("height")
    private Integer graphHeight;

    private String node;

    private Boolean type;

    private Boolean isEnable;
}

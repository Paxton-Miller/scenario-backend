package com.ogms.scenario.domain.vo.scenarioRelation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogms.scenario.domain.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @name: ScenarioRelationVo
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 9:01 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回的边数据形式")
public class ScenarioRelationVo extends BaseEntity {

    @JsonProperty("label")
    private String name;

    private Integer projectId;

    @JsonProperty("source")
    private Integer sourceId;

    @JsonProperty("target")
    private Integer targetId;

    private Boolean type;
}

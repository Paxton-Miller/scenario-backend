package com.ogms.scenario.domain.vo.scenario;

import com.ogms.scenario.domain.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @name: ScenarioInvolvedVo
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/25/2024 6:24 PM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@ApiModel(description = "返回的带所属项目信息的数据")
public class ScenarioInvolvedVo extends BaseEntity {

    private Integer projectId;

    private String projectName;

    private String name;

    private String roomUUID;

}

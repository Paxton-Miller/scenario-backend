package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationAddDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.entity.ScenarioRelation;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import com.ogms.scenario.domain.vo.scenarioRelation.ScenarioRelationVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @name: ScenarioRelationConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 3:26 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ScenarioRelationConverter {
    ScenarioRelation dto2Po(ScenarioRelationAddDto scenarioRelationAddDto);

    List<ScenarioRelation> dtoList2PoList(List<ScenarioRelationAddDto> scenarioRelationAddDtoList);

    ScenarioRelationVo po2Vo(ScenarioRelation scenarioRelation);

    List<ScenarioRelationVo> poList2VoList(List<ScenarioRelation> scenarioRelationList);
}

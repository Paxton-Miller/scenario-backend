package com.ogms.scenario.domain.converter;

import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @name: ScenarioConverter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 3:26 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface ScenarioConverter {
    Scenario dto2Po(ScenarioAddDto scenarioAddDto);

    List<Scenario> dtoList2PoList(List<ScenarioAddDto> scenarioAddDtos);

    ScenarioVo po2Vo(Scenario scenario);

    List<ScenarioVo> poList2VoList(List<Scenario> scenarioList);
}

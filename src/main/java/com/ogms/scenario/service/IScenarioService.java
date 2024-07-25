package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioAddDto;
import com.ogms.scenario.domain.dto.scenario.ScenarioEditDto;
import com.ogms.scenario.domain.dto.user.UserAddDto;
import com.ogms.scenario.domain.entity.Project;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.vo.scenario.ScenarioVo;

import java.util.List;

/**
 * @name: IScenarioService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:30 PM
 * @version: 1.0
 */
public interface IScenarioService extends IService<Scenario> {
    List<ScenarioVo> getAllScenario();

    List<ScenarioVo> getAllScenarioByProjectId(Integer id);

    BaseResultDto saveScenarioGraphJsonByIdAndType(Integer id, String type, String graph);

    BaseResultDto getScenarioGraphJsonByIdAndType(Integer id, String type);

    BaseResultDto addScenario(Integer createUserId, ScenarioAddDto scenarioAddDto);

    BaseResultDto editScenario(Integer modifyUserId, ScenarioEditDto scenarioEditDto);

    BaseResultDto batchAddScenario(Integer createUserId, List<ScenarioAddDto> scenarioAddDtoList);

    BaseResultDto batchEditScenario(Integer modifyUserId, List<ScenarioEditDto> scenarioEditDtoList);

    BaseResultDto delScenario(Integer id);

    BaseResultDto batchDelScenario(List<Integer> idList);
}

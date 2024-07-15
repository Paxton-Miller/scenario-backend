package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationAddDto;
import com.ogms.scenario.domain.dto.scenarioRelation.ScenarioRelationEditDto;
import com.ogms.scenario.domain.entity.ScenarioRelation;
import com.ogms.scenario.domain.vo.scenarioRelation.ScenarioRelationVo;

import java.util.List;

/**
 * @name: IScenarioRelationService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:33 PM
 * @version: 1.0
 */
public interface IScenarioRelationService extends IService<ScenarioRelation> {

    List<ScenarioRelationVo> getAllScenarioRelation();

    List<ScenarioRelationVo> getAllScenarioRelationByProjectId(Integer id);

    BaseResultDto addScenarioRelation(Integer createUserId, ScenarioRelationAddDto scenarioRelationAddDto);

    BaseResultDto batchAddScenarioRelation(Integer createUserId, List<ScenarioRelationAddDto> scenarioRelationAddDtoList);

    BaseResultDto editScenarioRelation(Integer modifyUserId, ScenarioRelationEditDto scenarioRelationEditDto);

    BaseResultDto batchEditScenarioRelation(Integer modifyUserId, List<ScenarioRelationEditDto> scenarioRelationEditDtoList);

    BaseResultDto delScenarioRelation(Integer id);

    BaseResultDto batchDelScenarioRelation(List<Integer> idList);
}

package com.ogms.scenario.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ogms.scenario.domain.entity.Scenario;
import com.ogms.scenario.domain.entity.User;
import com.ogms.scenario.domain.vo.scenario.ScenarioInvolvedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @name: ScenarioMapper
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/12/2024 2:32 PM
 * @version: 1.0
 */
public interface ScenarioMapper extends BaseMapper<Scenario>{
    IPage<ScenarioInvolvedVo> selectPageByIds(Page<ScenarioInvolvedVo> page, @Param("idList") List<Integer> idList);
}

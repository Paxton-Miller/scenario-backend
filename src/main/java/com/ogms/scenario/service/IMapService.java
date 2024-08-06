package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.map.MapAddDto;
import com.ogms.scenario.domain.dto.resource.ResourceAddDto;
import com.ogms.scenario.domain.entity.Map;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.service.common.IBaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name: IMapService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:29 PM
 * @version: 1.0
 */
public interface IMapService extends IService<Map>, IBaseService<Map, MapAddDto, Void, Void> {
    PaginationResultVo getAllMapByUserId(Integer createUserId, BaseQueryDto query);

    PaginationResultVo getAllOpenMap(BaseQueryDto query);

    BaseResultDto addMap(Integer createUserId, MapAddDto mapAddDto);

    BaseResultDto delMap(Integer id);
}

package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.converter.MapConverter;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.map.MapAddDto;
import com.ogms.scenario.domain.entity.Map;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.mapper.MapMapper;
import com.ogms.scenario.service.IMapService;
import com.ogms.scenario.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @name: MapServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/1/2024 10:32 PM
 * @version: 1.0
 */
@Service
//public class MapServiceImpl extends ServiceImpl<MapMapper, Map> implements IMapService {
public class MapServiceImpl extends BaseServiceImpl<Map, MapAddDto, Void, Void> implements IMapService {

    @Autowired
    private MapMapper mapMapper;

    @Resource
    private MapConverter mapConverter;

    @Override
    protected BaseMapper<Map> getMapper() {
        return mapMapper;
    }

    @Override
    protected Map dto2Po(MapAddDto addDto) {
        // Convert MapAddDto to Map entity for the baseService
        return mapConverter.dto2Po(addDto);
    }

    @Override
    protected Map getById(Integer id) {
        return mapMapper.selectById(id);
    }

    @Override
    public PaginationResultVo getAllMapByUserId(Integer createUserId, BaseQueryDto query) {
        QueryWrapper<Map> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("create_user_id", createUserId)
                .eq("access_level", "restricted");

        return paginate(queryWrapper, query);
    }

    @Override
    public PaginationResultVo getAllOpenMap(BaseQueryDto query) {
        QueryWrapper<Map> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("access_level", "open");

        return paginate(queryWrapper, query);
    }

    @Override
    public BaseResultDto addMap(Integer createUserId, MapAddDto mapAddDto) {
        try {
            Map map = mapConverter.dto2Po(mapAddDto);
            map.setCreateUserId(createUserId);
            mapMapper.insert(map);
            map = mapMapper.selectById(map.getId());
            return new BaseResultDto<>(true, map);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto delMap(Integer id) {
        try {
            Map map = this.getById(id);
            map.setIsDeleted(true);
            mapMapper.updateById(map);
            return new BaseResultDto<>(true, true);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }
}

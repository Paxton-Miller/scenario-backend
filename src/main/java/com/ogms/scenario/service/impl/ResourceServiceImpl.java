package com.ogms.scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.scenario.domain.constants.Constants;
import com.ogms.scenario.domain.converter.ResourceConverter;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.resource.ResourceAddDto;
import com.ogms.scenario.domain.entity.Resource;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import com.ogms.scenario.mapper.ResourceMapper;
import com.ogms.scenario.mapper.ScenarioMapper;
import com.ogms.scenario.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

/**
 * @name: ResourceServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 8:02 PM
 * @version: 1.0
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @javax.annotation.Resource
    private ResourceConverter resourceConverter;

    @Override
    public PaginationResultVo getAllResourceByUserId(Integer createUserId, BaseQueryDto query) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("create_user_id", createUserId)
                .eq("access_level", "restricted");
        Page<Resource> page = new Page<>(query.getPage(), query.getPageSize());
        resourceMapper.selectPage(page, queryWrapper);

        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    @Override
    public PaginationResultVo getAllOpenResource(BaseQueryDto query) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("access_level", "open");
        Page<Resource> page = new Page<>(query.getPage(), query.getPageSize());
        resourceMapper.selectPage(page, queryWrapper);

        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    @Override
    public BaseResultDto uploadResource(String uuidName, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String uploadDir = Constants.RESOURCE_UPLOAD_PATH;
            File pendingFile = new File(uploadDir + File.separator + uuidName);
            file.transferTo(pendingFile);
            return new BaseResultDto<>(true, "http://localhost:8898/resource/" + uuidName);
        } catch (IOException e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
    }

    @Override
    public BaseResultDto addResource(Integer createUserId, ResourceAddDto resourceAddDto) {
        try {
            Resource resource = resourceConverter.dto2Po(resourceAddDto);
            resource.setCreateUserId(createUserId);
            resourceMapper.insert(resource);
            resource = resourceMapper.selectById(resource.getId());
            return new BaseResultDto<>(true, resource);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }
}

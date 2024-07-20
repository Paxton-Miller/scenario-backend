package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.resource.ResourceAddDto;
import com.ogms.scenario.domain.entity.Resource;
import com.ogms.scenario.domain.vo.PaginationResultVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name: IResourceService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/17/2024 8:02 PM
 * @version: 1.0
 */
public interface IResourceService extends IService<Resource> {

    PaginationResultVo getAllResourceByUserId(Integer createUserId, BaseQueryDto query);

    PaginationResultVo getAllOpenResource(BaseQueryDto query);

    BaseResultDto uploadResource(String uuidName, MultipartFile file);

    BaseResultDto addResource(Integer createUserId, ResourceAddDto resourceAddDto);
}

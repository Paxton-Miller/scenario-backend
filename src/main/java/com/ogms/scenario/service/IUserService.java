package com.ogms.scenario.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ogms.scenario.domain.dto.common.BaseQueryDto;
import com.ogms.scenario.domain.dto.common.BaseResultDto;
import com.ogms.scenario.domain.dto.user.UserAddDto;
import com.ogms.scenario.domain.dto.user.UserLoginDto;
import com.ogms.scenario.domain.dto.user.UserQueryDto;
import com.ogms.scenario.domain.entity.User;
import com.ogms.scenario.domain.vo.PaginationResultVo;

/**
 * @name: IUserService
 * @description: 用户服务接口
 * @author: Lingkai Shi
 * @date: 4/10/2024 7:18 PM
 * @version: 1.0
 */
public interface IUserService extends IService<User> {
    // IService 是一个泛型接口，它定义了一组对实体类进行操作的通用方法，如增、删、改、查等

    PaginationResultVo getAllUser(BaseQueryDto query);

    PaginationResultVo getUserByIds(UserQueryDto query);

    BaseResultDto login(UserLoginDto userLoginDto);

    BaseResultDto logout();

    BaseResultDto addUser(UserAddDto userAddDto);

    BaseResultDto addUser(Integer createUserId, UserAddDto userAddDto);
}

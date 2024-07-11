package com.ogms.scenario.filter.handler;

import com.alibaba.fastjson.JSON;
import com.ogms.scenario.common.R;
import com.ogms.scenario.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @name: AuthenticationEntryPointImpl
 * @description: 自定义处理认证异常
 * @author: Lingkai Shi
 * @date: 5/10/2024 1:57 PM
 * @version: 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R result = R.fail(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录");
        String json = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(response, json);
    }
}

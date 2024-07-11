package com.ogms.scenario.filter.handler;

import com.alibaba.fastjson.JSON;
import com.ogms.scenario.common.R;
import com.ogms.scenario.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @name: AccessDeniedHandlerImp
 * @description: 自定义授权异常
 * @author: Lingkai Shi
 * @date: 5/10/2024 2:01 PM
 * @version: 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result = R.fail(HttpStatus.FORBIDDEN.value(), "无权访问");
        String json = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(response, json);
    }
}

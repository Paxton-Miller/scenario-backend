package com.ogms.scenario.filter;

import com.ogms.scenario.domain.dto.user.LoginUserDetailsDto;
import com.ogms.scenario.utils.JwtUtils;
import com.ogms.scenario.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @name: JwtAuthenticationTokenFilter
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 5/10/2024 11:08 AM
 * @version: 1.0
 */
// token认证过滤器
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头token
        String token = JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"));
        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token获取id
        Integer id;
        try {
            Map<String, Object> map = JwtUtils.decodeToken(token);
            id = (Integer) map.get("id");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token解析失败");
        }
        // 通过id从redis获取LoginUserDetails
        LoginUserDetailsDto loginUserDetailsDto = redisCache.getCacheObject("login:" + id.toString().trim());
        // TODO 还需要把权限信息封装进去

        if (Objects.isNull(loginUserDetailsDto)) {
            throw new RuntimeException("用户未登录");
        }
        // 将 Authentication对象存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetailsDto, null, loginUserDetailsDto.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}

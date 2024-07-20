package com.ogms.scenario.filter;

import com.ogms.scenario.domain.dto.user.LoginUserDetailsDto;
import com.ogms.scenario.utils.JwtUtils;
import com.ogms.scenario.utils.RedisCache;
import com.sun.javafx.fxml.builder.URLBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @name: WsConfig
 * @description: WebSocket握手拦截器
 * @author: Lingkai Shi
 * @date: 7/18/2024 4:32 PM
 * @version: 1.0
 */
@Slf4j
@Component
public class WsInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info(request.getRemoteAddress().toString());
        log.info("开始握手");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info(request.getRemoteAddress().toString());
        log.info("完成握手");
        response.getHeaders().set("Sec-WebSocket-Protocol", request.getHeaders().get("Sec-WebSocket-Protocol").toString());
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

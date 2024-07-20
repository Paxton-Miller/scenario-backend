package com.ogms.scenario.config;

import com.ogms.scenario.filter.WsInterceptor;
import com.ogms.scenario.filter.wsHandler.WsChatHandler;
import com.ogms.scenario.filter.wsHandler.WsGraphHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @name: WsConfig
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/18/2024 5:01 PM
 * @version: 1.0
 */
@Configuration
@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {

    @Resource
    WsChatHandler wsChatHandler;

    @Resource
    WsGraphHandler wsGraphHandler;

    @Resource
    WsInterceptor wsInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsChatHandler, "/chatWs")
                .addInterceptors(wsInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(wsGraphHandler, "/graphWs")
                .addInterceptors(wsInterceptor)
                .setAllowedOrigins("*");
    }
}

package com.ogms.scenario.config;

import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.filter.WsInterceptor;
import com.ogms.scenario.filter.wsHandler.WsChatHandler;
import com.ogms.scenario.filter.wsHandler.WsGraphHandler;
import com.ogms.scenario.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IRoomService roomService;

    private List<String> getAllRoomUUIDs() {
        return roomService.list().stream().map(Room::getUuid)
                .collect(Collectors.toList());
    }

    @Bean
    public WsChatHandler getWsChatHandler() {
        return new WsChatHandler();
    }

    @Bean
    public WsGraphHandler getWsGraphHandler() {
        return new WsGraphHandler();
    }

    @Bean
    public WsInterceptor getWsInterceptor() {
        return new WsInterceptor();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        List<String> roomUUIDs = getAllRoomUUIDs();
//        for (String uuid : roomUUIDs) {
//            registry.addHandler(getWsChatHandler(), "/chatWs/" + uuid)
//                    .addInterceptors(getWsInterceptor())
//                    .setAllowedOrigins("*");
//            registry.addHandler(getWsGraphHandler(), "/graphWs/" + uuid)
//                    .addInterceptors(getWsInterceptor())
//                    .setAllowedOrigins("*");
//        }
        registry.addHandler(getWsChatHandler(), "/chatWs/*")
                .addInterceptors(getWsInterceptor())
                .setAllowedOrigins("*");
        registry.addHandler(getWsGraphHandler(), "/graphWs/**")
                .addInterceptors(getWsInterceptor())
                .setAllowedOrigins("*");
    }
}

package com.ogms.scenario.filter;

import com.ogms.scenario.domain.dto.user.LoginUserDetailsDto;
import com.ogms.scenario.domain.entity.Room;
import com.ogms.scenario.service.IRoomService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private IRoomService roomService;

    private List<String> getAllRoomUUIDs() {
        return roomService.list().stream().map(Room::getUuid)
                .collect(Collectors.toList());
    }

    // 方法：查找字符串中第n次出现指定字符的位置
    private static int findNthOccurrence(String str, char c, int n) {
        int pos = str.indexOf(c);
        while (--n > 0 && pos != -1) {
            pos = str.indexOf(c, pos + 1);
        }
        return pos;
    }

    // 从ws url中提取dimension type
    private static String extractType(String url) {
        int pos1 = findNthOccurrence(url, '/', 4);
        int pos2 = url.lastIndexOf('/');
        if (pos1 == pos2) {
            return "";
        } else {
            return url.substring(pos1 + 1, pos2);
        }
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        List<String> allRoomUUIDs = getAllRoomUUIDs();
        String url = request.getURI().toString();
        String uuid = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf("?token"));
        String type = extractType(url);
        log.info(request.getRemoteAddress().toString());
        log.info("开始握手");
        if (allRoomUUIDs.contains(uuid)) {
            attributes.put("uuid", uuid);
            attributes.put("type", type);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info(request.getRemoteAddress().toString());
        log.info("完成握手");
//        response.getHeaders().set("Sec-WebSocket-Protocol", request.getHeaders().get("Sec-WebSocket-Protocol").toString());
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

package com.ogms.scenario.filter.wsHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogms.scenario.domain.dto.session.Message;
import com.ogms.scenario.domain.dto.session.SessionBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

// Let WsGraphHandler and WsChatHandler use the same sessionBeanMap
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static com.ogms.scenario.filter.wsHandler.WsChatHandler.sessionBeanMap;
import static com.ogms.scenario.filter.wsHandler.WsChatHandler.user;
import static com.ogms.scenario.filter.wsHandler.WsChatHandler.clientIdMaker;
import static com.ogms.scenario.filter.wsHandler.WsChatHandler.userId;

/**
 * @name: WsGraphHandler
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 8:01 PM
 * @version: 1.0
 */
@Slf4j
@Component
public class WsGraphHandler extends AbstractWebSocketHandler {
    private ObjectMapper objectMapper = new ObjectMapper(); // 将消息对象转换为json字符串

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        if (payload.equals("ping")) {
            return;
        }
        Message msg = new Message(payload, userId, true, new Date());
        sendMessage(sessionBeanMap, objectMapper.writeValueAsString(msg));
    }

    private void sendMessage(Map<String, SessionBean> sessionBeanMap, String jsonMsg) throws IOException {
        // 给每一个加入群聊的人发消息
        for (String key : sessionBeanMap.keySet()) {
            if (sessionBeanMap.get(key).getWebSocketSession().isOpen()) // new TextMessage(stringBuffer.toString())
                sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage(jsonMsg));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if (session.isOpen()) {
            session.close();
        }
        sessionBeanMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}

package com.ogms.scenario.filter.wsHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogms.scenario.domain.dto.session.Message;
import com.ogms.scenario.domain.dto.session.SessionBean;
import com.ogms.scenario.domain.entity.User;
import com.ogms.scenario.service.IUserService;
import com.ogms.scenario.utils.JwtUtils;
import com.ogms.scenario.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @name: WsHandler
 * @description: WebSocket主处理程序
 * @author: Lingkai Shi
 * @date: 7/18/2024 4:43 PM
 * @version: 1.0
 */
@Slf4j
@Component
public class WsChatHandler extends AbstractWebSocketHandler {
    @Autowired
    IUserService userService;

    private ObjectMapper objectMapper = new ObjectMapper(); // 将消息对象转换为json字符串

    protected static Map<String, SessionBean> sessionBeanMap;
    protected static AtomicInteger clientIdMaker; // 考虑线程安全
    protected static Integer userId = 0;
    protected static User user;

    static {
        sessionBeanMap = new ConcurrentHashMap<>();
        clientIdMaker = new AtomicInteger(0);
    }

    private Integer getUserIdFromSession(WebSocketSession session) {
        String query = session.getUri().getQuery();
        String token = query.substring(query.lastIndexOf('=') + 1, query.length());
        System.out.println(token);
        Map<String, Object> map = JwtUtils.decodeToken(token);
        Integer userId = (Integer) map.get("id");
        return userId;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // 获取session uri中的token，解析当前用户信息
        userId = getUserIdFromSession(session);
        user = userService.getById(userId);
        SessionBean sessionBean = new SessionBean(session, clientIdMaker.getAndIncrement(), userId); // 返回并自增
        sessionBeanMap.put(session.getId(), sessionBean);
        log.info(sessionBeanMap.get(session.getId()).getClientId() + "establish the connection");
        sendMessage(sessionBeanMap, objectMapper.writeValueAsString(new Message(user.getName() + " just joined the chat", userId, false, new Date())));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        if (payload.equals("ping")) {
            return;
        }
        log.info(sessionBeanMap.get(session.getId()).getClientId() + ":" + message.getPayload());
        sendMessage(sessionBeanMap, objectMapper.writeValueAsString(new Message(payload, sessionBeanMap.get(session.getId()).getUserId(), true, new Date())));
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
        Integer userId = sessionBeanMap.get(session.getId()).getUserId();
        log.info(sessionBeanMap.get(session.getId()).getClientId() + "Close the connection");
        sessionBeanMap.remove(session.getId());
        Message msg = new Message(user.getName() + " just quit the chat", userId, false, new Date());
        sendMessage(sessionBeanMap, objectMapper.writeValueAsString(msg));
    }

//    @Scheduled(fixedRate = 2000)
//    public void sendMsg() throws IOException {
//        for (String key : sessionBeanMap.keySet()) {
//            sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage("心跳"));
//        }
//    }

    private void sendMessage(Map<String, SessionBean> sessionBeanMap, String jsonMsg) throws IOException {
        // 给每一个加入群聊的人发消息
        for (String key : sessionBeanMap.keySet()) {
            if (sessionBeanMap.get(key).getWebSocketSession().isOpen()) // new TextMessage(stringBuffer.toString())
                sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage(jsonMsg));
        }
    }
}

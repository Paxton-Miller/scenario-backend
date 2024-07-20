package com.ogms.scenario.domain.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * @name: SessionBean
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/18/2024 4:46 PM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class SessionBean {

    private WebSocketSession webSocketSession;

    private Integer clientId;

    private Integer userId;
}

package com.ogms.scenario.domain.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @name: RoomManageDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/23/2024 8:43 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomManageDto {
    public Map<String, SessionBean> sessionBeanMap = new ConcurrentHashMap<>();

    public AtomicInteger clientIdMaker = new AtomicInteger(0);

    private String uuid;

    public RoomManageDto(String uuid) {
        this.uuid = uuid;
    }
}

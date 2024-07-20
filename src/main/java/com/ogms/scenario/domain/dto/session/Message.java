package com.ogms.scenario.domain.dto.session;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @name: Message
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 7/19/2024 10:14 AM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class Message {
    private String content;

    private Integer userId;

    private Boolean isMsg;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}

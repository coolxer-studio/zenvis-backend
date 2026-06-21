package com.coolxer.model.dih.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatDto {

    /**
     * 会话ID
     */
    private String chatId;

    /**
     * 模型
     */
    private String model;

    /**
     * 会话类型
     */
    private String type;

    /**
     * 会话内容
     */
    @NotBlank(message = "消息内容不能为空")
    private String message;

    /**
     * 是否深度思考
     */
    private Boolean deepThink;

    /**
     * 是否在线检索
     */
    private Boolean onlineSearch;

}

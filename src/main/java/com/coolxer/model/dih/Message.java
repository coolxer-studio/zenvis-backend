package com.coolxer.model.dih;

import com.coolxer.commons.enums.MessageType;
import com.coolxer.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String sender;
    private String content;
    private String time;
    private Boolean isError;
    private Boolean effective;
    /**
     * 消息类型: text, chart, code, table, image, etc.
     * 序列化和反序列化已在 JacksonConfig 中全局配置
     */
    private MessageType type;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.time = DateUtil.getCurrentDateTime();
        this.type = MessageType.TEXT;
    }

    public Message(String sender, String content, String type) {
        this.sender = sender;
        this.content = content;
        this.time = DateUtil.getCurrentDateTime();
        this.type = MessageType.fromCode(type);
    }

    public Message(String sender, String content, MessageType type) {
        this.sender = sender;
        this.content = content;
        this.time = DateUtil.getCurrentDateTime();
        this.type = type;
    }
}

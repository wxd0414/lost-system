package com.example.lostsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

@EqualsAndHashCode(callSuper = true)
@Table(name = "chat_history")
@Entity
@Data
@Comment("聊天历史")
public class ChatHistoryEntity extends BaseEntity {

    @Comment("会话id")
    private String sessionId;

    @Comment("角色")
    private String role;

    @Comment("内容")
    @Lob
    private String content;
}
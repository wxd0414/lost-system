package com.example.lostsystem.service;

import com.example.lostsystem.dto.ChatHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface AiChatService {
    public String chat(String  userId ,String message);

    Page<ChatHistory> queryChatHistory(String userId, Pageable pageable);

    void clearChatHistory(String userId);
}

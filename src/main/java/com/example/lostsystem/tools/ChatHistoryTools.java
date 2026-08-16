package com.example.lostsystem.tools;

import com.example.lostsystem.dto.ChatHistory;
import com.example.lostsystem.entity.ChatHistoryEntity;
import com.example.lostsystem.repository.ChatHistoryRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHistoryTools {


    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Tool("获取用户聊天历史")
    public List<ChatHistory> getChatHistory(@P("sessionId") String userId) {
        List<ChatHistoryEntity> chatHistoryEntities = chatHistoryRepository.findTop10BySessionIdOrderByCreatedDateDesc(userId);
        List<ChatHistory> chatHistories = new ArrayList<>();
        for (ChatHistoryEntity chatHistoryEntity : chatHistoryEntities){
            ChatHistory  chatHistory = new ChatHistory();
            BeanUtils.copyProperties(chatHistoryEntity, chatHistory);
            chatHistories.add(chatHistory);
        }
        return chatHistories;
    }
}

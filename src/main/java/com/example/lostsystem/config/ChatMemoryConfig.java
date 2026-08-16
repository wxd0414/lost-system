package com.example.lostsystem.config;


import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryConfig {

    @Bean
    public ChatMemoryProvider chatMemoryProvider(ChatMemoryStore chatMemoryStore){

        return id -> new MessageWindowChatMemory.Builder().maxMessages(20)
                .id(id).chatMemoryStore(chatMemoryStore).build();

    }

    /**
     * 意图识别专用记忆，与业务助手的记忆完全隔离，
     * 避免业务助手的工具调用历史污染意图识别上下文，导致 LLM 幻觉调用不存在的工具
     */
    @Bean
    public ChatMemoryProvider intentionChatMemoryProvider(){

        return id -> new MessageWindowChatMemory.Builder().maxMessages(20)
                .id("intention:" + id).chatMemoryStore(new InMemoryChatMemoryStore()).build();

    }

    @Bean
    public ChatMemoryStore chatMemoryStore() {
        return new InMemoryChatMemoryStore();
    }
}

package com.example.lostsystem.controller;


import com.example.lostsystem.aiService.AiAssistant;
import com.example.lostsystem.dto.ChatHistory;
import com.example.lostsystem.service.AiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private AiAssistant aiAssistant;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "Hello") String message,
                       @RequestParam(value = "userId", defaultValue = "111") String userId) {
        return aiAssistant.chat(userId, message);
    }

    @GetMapping(value = "/chat-stream", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    public Flux<String> chatStream(@RequestParam(value = "message", defaultValue = "Hello") String message,
                                   @RequestParam(value = "userId", defaultValue = "111") String userId) {
        return aiAssistant.chatStream(userId, message);
    }


    @Autowired
    private AiChatService aiChatService;

    @GetMapping(value = "/aiChat", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    public Flux<String> aiChat(@RequestParam(value = "message", defaultValue = "Hello") String message,
                                   @RequestParam(value = "userId", defaultValue = "111") String userId) {
        return Flux.just(aiChatService.chat(userId, message));
    }


    @GetMapping(value = "/chat-history")
    public PagedModel<ChatHistory> queryChatHistory(
            @RequestParam(value = "userId") String userId,
            @PageableDefault(direction = Sort.Direction.DESC, sort = "createdDate") Pageable pageable) {
        return new PagedModel<>(aiChatService.queryChatHistory(userId, pageable));
    }

    @PostMapping(value = "/clear-chat-history/{userId}")
    public ResponseEntity<Void> clearChatHistory(@PathVariable("userId") String userId) {
        aiChatService.clearChatHistory(userId);
        return ResponseEntity.ok().build();
    }
}

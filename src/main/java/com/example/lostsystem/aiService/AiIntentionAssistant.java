package com.example.lostsystem.aiService;


import com.example.lostsystem.aiOutput.IntentionOutput;
import com.example.lostsystem.aiOutput.LostItemRegisterOutput;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
           chatModel = "qwenChatModel",
           streamingChatModel = "qwenStreamingChatModel",
           chatMemoryProvider = "intentionChatMemoryProvider",
           tools = {"testTools","chatHistoryTools"})
@SystemMessage(fromResource = "get_intention.txt")
public interface AiIntentionAssistant {

    public String chat(@MemoryId String  memoryId, @UserMessage String userMessage);

    public Flux<String> chatStream(@MemoryId String  memoryId, @UserMessage String userMessage);


    /*@SystemMessage(fromResource = "get_intention.txt")*/
    @UserMessage("当前sessionId: {{sessionId}}; 用户当前消息: {{message}}")
    IntentionOutput intention(@V("sessionId") String  sessionId, @V("message") String message);

/*
    @SystemMessage(fromResource = "itemRegister.txt")
    @UserMessage("当前sessionId: {{sessionId}}; 用户当前消息: {{message}}")
    LostItemRegisterOutput lostItemRegister(@V("sessionId") String  sessionId, @V("message") String message);
*/

}

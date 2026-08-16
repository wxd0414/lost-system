package com.example.lostsystem.aop;


import com.example.lostsystem.entity.ChatHistoryEntity;
import com.example.lostsystem.repository.ChatHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ChatFlowAop {


    private static final Integer  USER_ROLE = 1;
    private static final Integer  SYSTEM_ROLE = 2;


    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Pointcut("@annotation(com.example.lostsystem.aop.ChatFlow)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args =joinPoint.getArgs();
        String sessionId = (String)args[0];
        String message = (String)args[1];
        log.info("sessionId: {}, message: {}", sessionId, message);
        saveChatHistory(sessionId, message ,USER_ROLE);
        Object  result = joinPoint.proceed();
        log.info("AI result: {}", result);
        saveChatHistory(sessionId, result.toString(), SYSTEM_ROLE);
        return result;
    }

    private void saveChatHistory(String sessionId, String message, Integer roleType) {

        ChatHistoryEntity chatHistoryEntity = new ChatHistoryEntity();
        chatHistoryEntity.setContent(message);
        chatHistoryEntity.setRole(roleType.toString());
        chatHistoryEntity.setSessionId(sessionId);
        chatHistoryRepository.save(chatHistoryEntity);
    }
}

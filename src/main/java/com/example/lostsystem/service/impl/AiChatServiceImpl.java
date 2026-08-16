package com.example.lostsystem.service.impl;

import com.example.lostsystem.aiOutput.FoundItemRegisterOutput;
import com.example.lostsystem.aiOutput.IntentionOutput;
import com.example.lostsystem.aiOutput.LostItemRegisterOutput;
import com.example.lostsystem.aiService.AiAssistant;
import com.example.lostsystem.aiService.AiIntentionAssistant;
import com.example.lostsystem.aop.ChatFlow;
import com.example.lostsystem.dto.ChatHistory;
import com.example.lostsystem.entity.ChatHistoryEntity;
import com.example.lostsystem.entity.FoundItemRegister;
import com.example.lostsystem.entity.LostItemRegister;
import com.example.lostsystem.repository.ChatHistoryRepository;
import com.example.lostsystem.repository.FoundItemRegisterRepository;
import com.example.lostsystem.repository.LostItemRegisterRepository;
import com.example.lostsystem.service.AiChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;


@Service
@Slf4j
public class AiChatServiceImpl implements AiChatService {


    @Autowired
    private AiAssistant aiAssistant;
    @Autowired
    private AiIntentionAssistant aiIntentionAssistant;

    @Autowired
    private LostItemRegisterRepository lostItemRegisterRepository;
    @Autowired
    private FoundItemRegisterRepository foundItemRegisterRepository;

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;


    @ChatFlow
    @Override
    public String chat(String userId ,String message) {
        try {
            //获取用户意图
            IntentionOutput intentionOutput = aiIntentionAssistant.intention(userId, message);
            log.info("用户意图：{}", intentionOutput.getIntention());
            log.info("用户意图输出：{}", intentionOutput.getOutput());
            String output = intentionOutput.getOutput();
            switch (intentionOutput.getIntention()) {
                case 1:
                    //失物信息登记
                    output = registerItem(userId, message);

                    break;
                case 2:
                    //捡到到失物登记
                    output = registerFoundItem(userId, message);
                    break;
                case 3:
                    //失物登记查询
                    output = queryLostItemRegister(userId,message);
                    break;
                case 4:
                    //捡到失物登记查询
                    output = queryFoundItemRegister(userId,message);
                    break;
                case 5:
                    //其他
                    break;
                default:
                    return intentionOutput.getOutput();
            }
            return output;
        } catch (Exception e) {
            log.error("AI对话处理异常，userId: {}, message: {}", userId, message, e);
            return "抱歉，系统处理您的请求时出现异常，请稍后重试。";
        }
    }

    @Override
    public Page<ChatHistory> queryChatHistory(String userId, Pageable pageable) {
        // 未指定排序时默认按创建时间升序，保证聊天记录按时间先后展示
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Direction.ASC, "createdDate"));
        }
        Page<ChatHistoryEntity> entityPage = chatHistoryRepository.findBySessionId(userId, pageable);
        return entityPage.map(entity -> {
            ChatHistory chatHistory = new ChatHistory();
            BeanUtils.copyProperties(entity, chatHistory);
            return chatHistory;
        });
    }

    @Transactional
    @Override
    public void clearChatHistory(String userId) {
        chatHistoryRepository.deleteBySessionId(userId);
    }

    /**
     * 丢失登记流程专用记忆ID：按用户+流程隔离，避免与捡到流程互相串扰
     */
    private String lostMemoryId(String userId) {
        return "lost:" + userId;
    }

    /**
     * 捡到登记流程专用记忆ID：按用户+流程隔离，避免与丢失流程互相串扰
     */
    private String foundMemoryId(String userId) {
        return "found:" + userId;
    }

    private String queryFoundItemRegister(String userId, String message) {

        log.info("用户{}查询捡到失物信息：{}", userId, message);
        // 复用登记提示词，其中包含根据手机号查询历史记录的工具调用逻辑
        FoundItemRegisterOutput output = aiAssistant.foundItemRegister( userId, message);
        return output.getOutput();
    }

    private String registerFoundItem(String userId, String message) {
        log.info("用户{}捡到登记失物信息：{}", userId, message);
        FoundItemRegisterOutput foundItemRegisterOutput =
                aiAssistant.foundItemRegister(userId, message);
        log.info("捡到失物登记AI输出：{}", foundItemRegisterOutput);
        log.info("捡到失物登记结果：{}", foundItemRegisterOutput.getOutput());
        if (foundItemRegisterOutput.getIsComplete()) {
            FoundItemRegister foundItemRegister = new FoundItemRegister();
            BeanUtils.copyProperties(foundItemRegisterOutput, foundItemRegister);
            log.info("捡到失物登记完成");
            foundItemRegisterRepository.save(foundItemRegister);
        }
        return foundItemRegisterOutput.getOutput();
    }

    private String queryLostItemRegister(String userId, String message) {
        log.info("用户{}查询失物信息：{}", userId, message);
        // 复用登记提示词，其中包含根据手机号查询历史记录的工具调用逻辑
        LostItemRegisterOutput output = aiAssistant.lostItemRegister( userId, message);
        return output.getOutput();
    }

    private String registerItem(String userId, String message) {
        log.info("用户{}登记失物信息：{}", userId, message);
        LostItemRegisterOutput lostItemRegisterOutput =
                aiAssistant.lostItemRegister(userId, message);
        log.info("失物登记AI输出：{}", lostItemRegisterOutput);
        log.info("失物登记结果：{}", lostItemRegisterOutput.getOutput());
        if (lostItemRegisterOutput.getIsComplete()) {
            LostItemRegister lostItemRegister;
            lostItemRegister = new LostItemRegister();
            BeanUtils.copyProperties(lostItemRegisterOutput, lostItemRegister);
            log.info("失物登记完成");
            lostItemRegisterRepository.save(lostItemRegister);
        }
        return lostItemRegisterOutput.getOutput();
    }
}

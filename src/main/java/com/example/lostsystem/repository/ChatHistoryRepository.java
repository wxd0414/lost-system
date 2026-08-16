package com.example.lostsystem.repository;

import com.example.lostsystem.entity.ChatHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatHistoryRepository extends BaseRepository<ChatHistoryEntity> {


    List<ChatHistoryEntity> findTop10BySessionIdOrderByCreatedDateDesc(String sessionId);

    Page<ChatHistoryEntity> findBySessionId(String sessionId, Pageable pageable);

    void deleteBySessionId(String sessionId);
}

package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.ai.ChatHistoryMessage;

public interface ChatMemoryService {
    void addMessage(String sessionId, String role, String content);
    List<ChatHistoryMessage> getRecentMessages(String sessionId, int limit);
    void clearHistory(String sessionId);
}


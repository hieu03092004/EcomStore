package com.fit.ecommerce.services;

import com.fit.ecommerce.dtos.response.ai.ChatAIResponse;

public interface AIService {
    ChatAIResponse chat(String message, Long customerId, String sessionId);
}


package com.fit.ecommerce.services;

import org.springframework.data.domain.Page;

import com.fit.ecommerce.dtos.request.feedback.CreateFeedbackRequest;
import com.fit.ecommerce.dtos.response.feedback.FeedbackResponse;
import com.fit.ecommerce.dtos.response.feedback.RatingStatisticsResponse;

public interface FeedbackService {
    FeedbackResponse createFeedback(CreateFeedbackRequest request);
    boolean checkIfReviewed(Long orderId, Long productVariantId);
    FeedbackResponse getFeedbackDetail(Long orderId, Long productVariantId);
    
    // Product page APIs
    Page<FeedbackResponse> getFeedbacksByProduct(Long productId, int page, int size, Integer rating);
    RatingStatisticsResponse getRatingStatistics(Long productId);
    
    // Admin APIs
    Page<FeedbackResponse> getAllFeedbacks(int page, int size, Integer rating, Boolean status, String fromDate, String toDate);
    FeedbackResponse getFeedbackById(Long id);
    void changeStatusFeedback(Long id);
    void deleteFeedback(Long id);
}

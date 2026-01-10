package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.response.rank.RankResponse;
import com.fit.ecommerce.entities.Order;
import com.fit.ecommerce.entities.Ranking;

public interface RankingService {
    Ranking getRankingEntityById(Long id);

    Ranking getRankingForSpending(Double spending);
    List<RankResponse> getAllRankings();
    
    void updateCustomerRanking(Order order);
    
    RankResponse getMyRank();
}

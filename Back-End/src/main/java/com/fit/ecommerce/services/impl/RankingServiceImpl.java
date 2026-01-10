package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.ecommerce.configurations.CacheConfig;
import com.fit.ecommerce.dtos.response.rank.RankResponse;
import com.fit.ecommerce.dtos.response.voucher.RankVoucherResponse;
import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.entities.Order;
import com.fit.ecommerce.entities.Ranking;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.RankingMapper;
import com.fit.ecommerce.repositories.CustomerRepository;
import com.fit.ecommerce.repositories.OrderRepository;
import com.fit.ecommerce.repositories.RankingRepository;
import com.fit.ecommerce.services.RankingService;
import com.fit.ecommerce.utils.SecurityUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final SecurityUtils securityUtils;

    @Override
    public Ranking getRankingEntityById(Long id) {
        return rankingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RANKING_NOT_FOUND));
    }

    @Override
    @Cacheable(value = CacheConfig.RANKING_CACHE, key = "'all'")
    public List<RankResponse> getAllRankings() {
        return rankingRepository.findAll()
                .stream()
                .map(rankingMapper::toRankResponse)
                .toList();
    }

    @Override
    public Ranking getRankingForSpending(Double spending) {
        return rankingRepository.findRankingBySpending(spending)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RANKING_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateCustomerRanking(Order order) {
        Customer customer = order.getCustomer();
        if (customer == null) {
            return;
        }

        Double currentSpending = customer.getTotalSpending();
        Double orderAmount = order.getFinalTotalPrice();
        
        if (orderAmount == null || orderAmount <= 0) {
            return;
        }

        Double newTotalSpending = (currentSpending == null ? 0.0 : currentSpending) + orderAmount;
        customer.setTotalSpending(newTotalSpending);

        Ranking newRank = getRankingForSpending(newTotalSpending);
        customer.setRanking(newRank);

        customerRepository.save(customer);
    }

    @Override
    public RankResponse getMyRank() {
        Customer currentCustomer = securityUtils.getCurrentCustomer();
        Double totalSpending = orderRepository.getTotalSpendingByCustomerId(currentCustomer.getId());
        if (totalSpending == null) {
            totalSpending = 0.0;
        }
        
        // Lấy rank dựa trên tổng tiền tích lũy
        Ranking ranking = getRankingForSpending(totalSpending);
        rankingRepository.save(ranking);
        return rankingMapper.toRankResponse(ranking);
    }
}

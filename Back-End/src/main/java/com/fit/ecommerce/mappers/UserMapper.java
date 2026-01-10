package com.fit.ecommerce.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fit.ecommerce.dtos.response.rank.RankResponse;
import com.fit.ecommerce.dtos.response.user.UserProfileResponse;
import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.entities.Ranking;
import com.fit.ecommerce.entities.Staff;
import com.fit.ecommerce.entities.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRolesToString(user.getUserRoles()))")
    UserProfileResponse toUserProfileResponse(User user);

    default List<String> mapRolesToString(java.util.List<com.fit.ecommerce.entities.UserRole> userRoles) {
        if (userRoles == null || userRoles.isEmpty()) {
            return List.of();
        }
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName())
                .toList();
    }

    @AfterMapping
    default void assignRankAfterMapping(User user, @MappingTarget UserProfileResponse response){
        // Assign rank for Customer
        if(user instanceof Customer customer){
            if(customer.getRanking() != null){
                Ranking rank = customer.getRanking();
                var rankResponse = new RankResponse();
                rankResponse.setId(rank.getId());
                rankResponse.setName(rank.getName());
                rankResponse.setDescription(rank.getDescription());
                rankResponse.setMinSpending(rank.getMinSpending());
                rankResponse.setMaxSpending(rank.getMaxSpending());
                rankResponse.setDiscountRate(rank.getDiscountRate());
                response.setRank(rankResponse);
            }
        }
        
        if(user instanceof Staff staff){
            response.setLeader(staff.getLeader());
        }
    }
}

package com.fit.ecommerce.dtos.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fit.ecommerce.dtos.response.rank.RankResponse;

@Getter
@Setter
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String fullName;
    private List<String> roles;
    private String avatar;
    private String phone;
    private RankResponse rank;
    private Boolean leader;
    private Double totalSpending;
}

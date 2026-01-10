package com.fit.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.ecommerce.dtos.response.base.ResponseSuccess;
import com.fit.ecommerce.dtos.response.rank.RankResponse;
import com.fit.ecommerce.dtos.response.role.RoleResponse;
import com.fit.ecommerce.dtos.response.voucher.RankVoucherResponse;
import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.services.RankingService;
import com.fit.ecommerce.services.RoleService;
import com.fit.ecommerce.utils.SecurityUtils;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("")
    public ResponseEntity<ResponseSuccess<List<RankResponse>>> getAllRank() {
        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Get rankings success",
                rankingService.getAllRankings()
        ));
    }

    @GetMapping("/my-rank")
    public ResponseEntity<ResponseSuccess<RankResponse>> getMyRank() {
        RankResponse rank = rankingService.getMyRank();
        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Get my rank success",
                rank
        ));
    }
}
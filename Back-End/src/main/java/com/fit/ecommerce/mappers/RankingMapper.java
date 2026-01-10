package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.rank.RankResponse;
import com.fit.ecommerce.dtos.response.voucher.RankVoucherResponse;
import com.fit.ecommerce.entities.Ranking;


@Mapper(componentModel = "spring")
public interface RankingMapper {

    RankVoucherResponse toRankVoucherResponse(Ranking ranking);

    RankResponse toRankResponse(Ranking ranking);
}

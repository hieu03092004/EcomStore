package com.fit.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fit.ecommerce.enums.DiscountType;
import com.fit.ecommerce.enums.PromotionType;

@Entity
@Table(name = "promotions")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Promotion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;

    private Double discount;
    private Boolean active;
    private Integer priority;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    private List<PromotionTarget> promotionTargets;
}

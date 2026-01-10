package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.ecommerce.entities.PurchaseOrderDetail;

import java.util.List;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Long> {
    List<PurchaseOrderDetail> findByPurchaseOrderId(Long purchaseOrderId);
}

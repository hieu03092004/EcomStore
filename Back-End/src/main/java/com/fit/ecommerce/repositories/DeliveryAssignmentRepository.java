package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fit.ecommerce.entities.DeliveryAssignment;
import com.fit.ecommerce.entities.Staff;
import com.fit.ecommerce.enums.DeliveryStatus;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Long> {

    Optional<DeliveryAssignment> findByOrder_Id(Long orderId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END " +
            "FROM DeliveryAssignment d " +
            "WHERE d.shipper = :shipper AND d.deliveryStatus = :status")
    boolean existsByShipperAndDeliveryStatus(@Param("shipper") Staff shipper,
                                             @Param("status") DeliveryStatus status);

    List<DeliveryAssignment> findByShipperAndDeliveryStatus(Staff shipper, DeliveryStatus status);

    List<DeliveryAssignment> findByShipper(Staff shipper);

    @Query("SELECT COUNT(d) FROM DeliveryAssignment d WHERE d.shipper = :shipper AND d.deliveryStatus = :status")
    long countByShipperAndDeliveryStatus(@Param("shipper") Staff shipper, @Param("status") DeliveryStatus status);
}
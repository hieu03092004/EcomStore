package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fit.ecommerce.entities.Customer;
import com.fit.ecommerce.entities.Voucher;
import com.fit.ecommerce.entities.VoucherCustomer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, Long> {
    List<VoucherCustomer> findAllByVoucher_Id(Long voucherId);


    @Query("""
    SELECT vc
    FROM VoucherCustomer vc
    WHERE vc.customer.id = :id
      AND vc.voucher.startDate <= :end
      AND vc.voucher.endDate >= :start
      AND vc.voucher.active = true
""")
    List<VoucherCustomer> findAllByCustomerIdAndVoucherDateBetweenAndReady(
            @Param("id") Long id,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

    boolean existsByVoucherAndCustomer(Voucher voucher, Customer customer);
}

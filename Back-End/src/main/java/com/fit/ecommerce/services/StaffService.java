package com.fit.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.staff.StaffAddRequest;
import com.fit.ecommerce.dtos.request.staff.StaffUpdateRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.staff.StaffResponse;
import com.fit.ecommerce.entities.Staff;

public interface StaffService {
    StaffResponse createStaff(StaffAddRequest staffAddRequest);

    PageResponse<StaffResponse> getStaffs(
            int page,
            int size,
            String staffName,
            String email,
            String phone,
            Boolean status,
            LocalDate joinDate,
            Long roleId
    );

    StaffResponse getStaffById(Long id);

    StaffResponse updateStaff(StaffUpdateRequest staffUpdateRequest, Long id);

    void changeActive(Long id);

    Staff getStaffEntityById(Long id);
    
    List<StaffResponse> getAllActiveStaffs();
}

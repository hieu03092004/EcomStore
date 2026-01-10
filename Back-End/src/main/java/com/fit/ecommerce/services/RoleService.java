package com.fit.ecommerce.services;


import java.util.List;

import com.fit.ecommerce.dtos.response.role.RoleResponse;

public interface RoleService {

    List<RoleResponse> getAllRolesForAdmin();
}

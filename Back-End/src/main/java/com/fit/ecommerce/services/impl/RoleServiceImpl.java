package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fit.ecommerce.dtos.response.role.RoleResponse;
import com.fit.ecommerce.entities.Role;
import com.fit.ecommerce.mappers.RoleMapper;
import com.fit.ecommerce.repositories.RoleRepository;
import com.fit.ecommerce.services.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAllRolesForAdmin() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .filter(role -> !"CUSTOMER".equalsIgnoreCase(role.getName()))
                .map(roleMapper::toResponse)
                .collect(Collectors.toList());
    }
}

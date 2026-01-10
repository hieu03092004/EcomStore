package com.fit.ecommerce.mappers;

import org.mapstruct.Mapper;

import com.fit.ecommerce.dtos.response.role.RoleResponse;
import com.fit.ecommerce.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toResponse(Role role);

}


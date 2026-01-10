package com.fit.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.ecommerce.dtos.response.base.ResponseSuccess;
import com.fit.ecommerce.dtos.response.role.RoleResponse;
import com.fit.ecommerce.services.RoleService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/for-admin")
    public ResponseEntity<ResponseSuccess<List<RoleResponse>>> getAllRolesForAdmin() {
        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Get roles success",
                roleService.getAllRolesForAdmin()
        ));
    }
}

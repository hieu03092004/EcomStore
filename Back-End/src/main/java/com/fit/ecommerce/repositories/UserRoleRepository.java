package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fit.ecommerce.entities.Role;
import com.fit.ecommerce.entities.User;
import com.fit.ecommerce.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByRole(Role role);
    void deleteByUser(User user);

}

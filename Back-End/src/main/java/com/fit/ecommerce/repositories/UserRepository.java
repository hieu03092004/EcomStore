package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
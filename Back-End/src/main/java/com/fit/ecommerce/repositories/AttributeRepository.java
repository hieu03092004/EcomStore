package com.fit.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.ecommerce.entities.Attribute;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {

    List<Attribute> findByStatus(boolean status);

}

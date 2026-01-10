package com.fit.ecommerce.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fit.ecommerce.dtos.response.attribute.AttributeResponse;
import com.fit.ecommerce.entities.Attribute;
import com.fit.ecommerce.exceptions.ErrorCode;
import com.fit.ecommerce.exceptions.custom.ResourceNotFoundException;
import com.fit.ecommerce.mappers.AttributeMapper;
import com.fit.ecommerce.repositories.AttributeRepository;
import com.fit.ecommerce.services.AttributeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Override
    public  List<AttributeResponse> getAttributesActive(){
        return attributeRepository.findByStatus(true).stream()
                .map(attributeMapper::toResponse)
                .toList();

    }

    @Override
    public Attribute getAttributeEntityById(Long id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.ATTRIBUTE_NOT_FOUND));
    }
}

package com.fit.ecommerce.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fit.ecommerce.dtos.response.chat.MessageResponse;
import com.fit.ecommerce.entities.Message;
import com.fit.ecommerce.entities.Staff;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "chatId", source = "chat.id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.fullName")
    @Mapping(target = "isStaff", ignore = true)
    MessageResponse toResponse(Message message);

    @AfterMapping
    default void setIsStaff(Message message, @MappingTarget MessageResponse response) {
        // Kiểm tra sender có phải là Staff không
        boolean isStaff = message.getSender() instanceof Staff;
        response.setIsStaff(isStaff);
    }
}


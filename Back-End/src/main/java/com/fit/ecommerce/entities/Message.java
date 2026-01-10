package com.fit.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fit.ecommerce.enums.MessageType;

@Entity
@Getter
@Setter
@Table(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}

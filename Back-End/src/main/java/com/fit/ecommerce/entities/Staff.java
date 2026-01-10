package com.fit.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import com.fit.ecommerce.enums.WorkStatus;

@Entity
@Getter
@Setter
@Table(name = "staffs")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Staff extends User {

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "work_status")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Column
    private Boolean leader;

    @Column
    private String address;

}

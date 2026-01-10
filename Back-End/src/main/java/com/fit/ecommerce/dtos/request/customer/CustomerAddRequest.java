package com.fit.ecommerce.dtos.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.address.AddressRequest;
import com.fit.ecommerce.entities.Cart;
import com.fit.ecommerce.entities.UserRole;

@Getter
@Setter
public class CustomerAddRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must have 10 digist")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String avatar;
//    private Double totalSpending;

    private AddressRequest address;

    private LocalDate dateOfBirth;

    private List<AddressRequest> addresses;

    private Long rankingId;

}

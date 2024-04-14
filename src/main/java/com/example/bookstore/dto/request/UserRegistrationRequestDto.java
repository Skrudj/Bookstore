package com.example.bookstore.dto.request;

import com.example.bookstore.exceptions.annotations.FieldMatch;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords did not match")
public class UserRegistrationRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String shippingAddress;
}

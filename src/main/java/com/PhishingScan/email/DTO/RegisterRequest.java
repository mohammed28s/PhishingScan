package com.PhishingScan.email.DTO;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {  // This is for creating new user

    @Pattern( regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "Invalid email format")
    @Size(min = 5, max = 40, message = "Username must be between 5 and 40 characters long")
    private String username;

    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Invalid password format")
    private String email;

    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Invalid password format")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

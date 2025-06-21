package com.PhishingScan.email.DTO;


import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {  // User Login

    @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "Invalid email")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Invalid password")
    private String password;

}

package com.PhishingScan.email.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {  // this is for generating a new token

    @JsonIgnore
    private String token;
}

package com.jiaul.cloudapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int id;
    private String name;
    private String email;
    private String role;
    private String message;
    private String token;
    private String tokenType;
}

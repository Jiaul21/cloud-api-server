package com.jiaul.cloudapi.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String userName;
    private String email;
    private String password;
}

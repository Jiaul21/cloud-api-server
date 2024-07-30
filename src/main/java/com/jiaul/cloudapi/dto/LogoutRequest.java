package com.jiaul.cloudapi.dto;

import lombok.Data;

@Data
public class LogoutRequest {

    private String email;
    private String tokenValue;
}

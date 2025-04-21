package com.E_Shop.user_service.dto;

import lombok.*;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}

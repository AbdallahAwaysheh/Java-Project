package com.E_Shop.user_service.dto;

import com.E_Shop.user_service.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Role role;

}

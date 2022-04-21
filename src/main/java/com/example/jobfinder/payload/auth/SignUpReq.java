package com.example.jobfinder.payload.auth;

import com.example.jobfinder.enums.UserRole;
import com.example.jobfinder.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Admin {
    private Company company;
}

@Getter
@Setter
public class SignUpReq {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Company company;
    private Admin admin;
}

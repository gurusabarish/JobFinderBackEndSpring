package com.example.jobfinder.payload;

import com.example.jobfinder.enums.UserRole;
import com.example.jobfinder.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateReq {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private Company company;
}

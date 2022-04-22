package com.example.jobfinder.payload.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateReq {
    private Long userId;
    private String name;
    private String description;
    private String email;
    private String website;
    private String phone;
}
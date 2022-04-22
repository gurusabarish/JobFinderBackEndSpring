package com.example.jobfinder.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String email;
    private String website;

//    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;
}

package com.example.jobfinder.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

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

//    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "hr_id")
//    private List Hrs;

//    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "admin_id", referencedColumnName = "id")
//    private Set<User> admin;
//
//    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "admin_id")
//    private List admins;
}

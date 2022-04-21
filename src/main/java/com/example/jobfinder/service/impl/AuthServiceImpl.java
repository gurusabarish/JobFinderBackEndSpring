package com.example.jobfinder.service.impl;


import com.example.jobfinder.enums.UserRole;
import com.example.jobfinder.model.Company;
import com.example.jobfinder.model.User;
import com.example.jobfinder.payload.auth.SignUpReq;
import com.example.jobfinder.repository.CompanyRepository;
import com.example.jobfinder.repository.UserRepository;
import com.example.jobfinder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public User signUp(Map<String, Object> req) {
        if(req.get("role").toString().equals(UserRole.ROLE_SUPER_ADMIN.toString())) {

            Company company = companyRepository.save((Company) req.get("company"));
            User user = new User();
            user.setCompany(company);
            user.setEmail(req.get("email").toString());
            user.setPassword(req.get("password").toString());
            user.setName(req.get("name").toString());
            user.setRole((UserRole) req.get("role"));

            user.setUpdatedAt(new Date());
            user.setCreatedAt(new Date());
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public User signIn(User user) {
        return null;
    }
}

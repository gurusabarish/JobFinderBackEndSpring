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
    @Override
    public User signUp(User req) {
        if(req.getRole() == UserRole.ROLE_ADMIN) {
            req.setEnabled(false);
        } else {
            req.setEnabled(true);
        }

        req.setCreatedAt(new Date());
        req.setUpdatedAt(new Date());
        User user = userRepository.save(req);
        user.setPassword(null);
        return user;
    }

    @Override
    public User signIn(Map req) {
        User user = userRepository.findByEmail(req.get("email").toString());
        if(user == null)
            return null;
        if(!user.getPassword().equals(req.get("password").toString()))
            return null;

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        user.setPassword(null);

        return user;
    }
}

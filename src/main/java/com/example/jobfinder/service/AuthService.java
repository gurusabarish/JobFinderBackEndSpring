package com.example.jobfinder.service;

import com.example.jobfinder.model.User;
import com.example.jobfinder.payload.auth.SignUpReq;

import java.util.Map;

public interface AuthService {
    User signUp(User req);
    User signIn(Map req);
    User getUserById(Long id);
}

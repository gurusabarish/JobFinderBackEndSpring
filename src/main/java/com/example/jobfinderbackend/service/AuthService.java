package com.example.jobfinderbackend.service;

import com.example.jobfinderbackend.model.UserModel;
import com.example.jobfinderbackend.payload.SignInReq;

import java.util.List;

public interface AuthService {
    UserModel signUp(UserModel user);
    UserModel signIn(SignInReq signInReq);

    UserModel findById(Long id);
    UserModel getUserByEmail(String email);

//    List<UserModel> findAll();
//    UserModel update(long id, UserModel user);
//    void delete(long id);
}

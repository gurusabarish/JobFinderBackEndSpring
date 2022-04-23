package com.example.jobfinderbackend.service.Impl;

import com.example.jobfinderbackend.model.UserModel;
import com.example.jobfinderbackend.model.UserRoleEnum;
import com.example.jobfinderbackend.payload.SignInReq;
import com.example.jobfinderbackend.repository.UserRepository;
import com.example.jobfinderbackend.service.AuthService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel signUp(UserModel user) {
        if(user.getRole().equals(UserRoleEnum.ROLE_ADMIN)){ user.setEnabled(false); }
        else { user.setEnabled(true); }
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }

    @Override
    public UserModel signIn(SignInReq signInReq) {
        return userRepository.findByEmailAndPassword(signInReq.getEmail(), signInReq.getPassword());
    }

    @Override
    public UserModel findById(Long id) {
        UserModel user = userRepository.findById(id).get();
        user.setPassword(null);
        return user;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

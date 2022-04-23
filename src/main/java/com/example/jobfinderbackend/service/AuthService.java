package com.example.jobfinderbackend.service;

import com.example.jobfinderbackend.model.UserModel;
import com.example.jobfinderbackend.payload.AddCompanyToUserReq;
import com.example.jobfinderbackend.payload.SignInReq;

import java.util.List;
import java.util.Map;

public interface AuthService {
    UserModel signUp(UserModel user);
    UserModel signIn(SignInReq signInReq);

    UserModel findById(Long id);
    UserModel getUserByEmail(String email);

    // Add companyID to user
    Map addCompanyToUser(AddCompanyToUserReq addCompanyToAdminReq);

    // Get admins by companyID
    List<UserModel> getAdminsByCompanyId(Long companyId);

//    List<UserModel> findAll();
//    UserModel update(long id, UserModel user);
//    void delete(long id);
}

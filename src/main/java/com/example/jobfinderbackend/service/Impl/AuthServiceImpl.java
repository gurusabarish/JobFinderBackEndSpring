package com.example.jobfinderbackend.service.Impl;

import com.example.jobfinderbackend.model.UserModel;
import com.example.jobfinderbackend.model.UserRoleEnum;
import com.example.jobfinderbackend.payload.AddCompanyToUserReq;
import com.example.jobfinderbackend.payload.SignInReq;
import com.example.jobfinderbackend.repository.UserRepository;
import com.example.jobfinderbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Add company to admin's company field
    @Override
    public Map addCompanyToUser(AddCompanyToUserReq addCompanyToAdminReq) {
        UserModel user = userRepository.findById(addCompanyToAdminReq.getUserId()).get();
        if(user.getRole().equals(UserRoleEnum.ROLE_ADMIN))
            user.setAdminCompanyId(addCompanyToAdminReq.getCompanyId());
        else if (user.getRole().equals(UserRoleEnum.ROLE_HR))
            user.setHRCompanyId(addCompanyToAdminReq.getCompanyId());

        userRepository.save(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);

        return response;
    }

    // Get admins by company id
    @Override
    public List<UserModel> getAdminsByCompanyId(Long companyId) {
        return userRepository.findAllByAdminCompanyId(companyId);
    }
}

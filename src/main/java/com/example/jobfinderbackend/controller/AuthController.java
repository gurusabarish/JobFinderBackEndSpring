package com.example.jobfinderbackend.controller;

import com.example.jobfinderbackend.model.UserModel;
import com.example.jobfinderbackend.payload.SignInReq;
import com.example.jobfinderbackend.service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> login(@RequestBody SignInReq signInReq) {
        UserModel user = authServiceImpl.signIn(signInReq);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody UserModel signUpReq) {
        return new ResponseEntity<>(authServiceImpl.signUp(signUpReq), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@RequestParam(value = "token") Long token) {
        return new ResponseEntity<>(authServiceImpl.findById(token), HttpStatus.OK);
    }
}

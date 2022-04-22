package com.example.jobfinder.controller;

import com.example.jobfinder.model.User;
import com.example.jobfinder.payload.auth.SignUpReq;
import com.example.jobfinder.service.impl.AuthServiceImpl;
import com.example.jobfinder.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    AuthServiceImpl authServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User req) {
        System.out.println(req);
        User userByEmail = userServiceImpl.findByEmail(req.getEmail());

        if (userByEmail != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "UserEmail already exists");
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.ok(authServiceImpl.signUp(req));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> req) {
        User user = authServiceImpl.signIn(req);
        if (user == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "UserEmail or password is incorrect");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") Long token) {
        return ResponseEntity.ok(authServiceImpl.getUserById(token));
    }
}

package com.example.jobfinder.controller;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.User;
import com.example.jobfinder.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping()
    public List<User> getAllUsers() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userServiceImpl.findByID(userId));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User userByEmail = userServiceImpl.findByEmail(user.getEmail());
        System.out.println(userByEmail);

        if (userByEmail != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Username already exists");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().body(userServiceImpl.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(userServiceImpl.update(userId, userDetails));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userServiceImpl.delete(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

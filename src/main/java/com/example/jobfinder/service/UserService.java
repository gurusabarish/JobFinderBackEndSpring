package com.example.jobfinder.service;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.User;

import java.util.List;

public interface UserService {
    User save(User req);
    User findByEmail(String username);
    User findByID(Long id) throws ResourceNotFoundException;
    List<User> findAll();
    User update(Long id, User user) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
}

package com.example.jobfinder.service.impl;

import com.example.jobfinder.enums.UserRole;
import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.User;
import com.example.jobfinder.repository.UserRepository;
import com.example.jobfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User save(User user) {
        if (user.getRole() == UserRole.ROLE_ADMIN) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByID(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    @Override
    public User update(Long id, User user) throws ResourceNotFoundException {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userToUpdate.setEmail(user.getEmail() != null ? user.getEmail() : userToUpdate.getEmail());
        userToUpdate.setPassword(user.getPassword() != null ? user.getPassword() : userToUpdate.getPassword());
        userToUpdate.setName(user.getName() != null ? user.getName() : userToUpdate.getName());
        userToUpdate.setRole(user.getRole() != null ? user.getRole() : userToUpdate.getRole());
        userToUpdate.setCompany(user.getCompany() != null ? user.getCompany() : userToUpdate.getCompany());

        userToUpdate.setUpdatedAt(new Date());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(id);
    }
}

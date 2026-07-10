package com.helperhub.service;

import com.helperhub.entity.User;
import com.helperhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    // Register User
    public User saveUser(User user) {

        // Save User
        User savedUser = repository.save(user);

        // Send Welcome Email
        emailService.sendEmail(
                savedUser.getEmail(),
                "Welcome to HelperHub",
                "Hello " + savedUser.getName()
                        + ",\n\nWelcome to HelperHub!"
                        + "\n\nYour account has been created successfully."
                        + "\n\nThank you for registering with HelperHub."
                        + "\n\nRegards,"
                        + "\nHelperHub Team"
        );

        return savedUser;
    }

    // Get All Users
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Get User By ID
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    // Update User
    public User updateUser(Long id, User user) {

        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPhone(user.getPhone());
        existing.setPassword(user.getPassword());

        return repository.save(existing);
    }

    // Delete User
    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("User Not Found");
        }

        repository.deleteById(id);
    }
}
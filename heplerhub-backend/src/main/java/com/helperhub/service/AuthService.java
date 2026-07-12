package com.helperhub.service;

import com.helperhub.dto.LoginRequest;
import com.helperhub.dto.LoginResponse;
import com.helperhub.dto.RegisterRequest;
import com.helperhub.dto.WorkerRegisterRequest;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;
import com.helperhub.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User register(RegisterRequest request) {

        if (userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("USER");

        return userRepository.save(user);
    }

    @Transactional
    public Worker registerWorker(
            WorkerRegisterRequest request) {

        if (userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        if (workerRepository
                .existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Worker email already registered"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("WORKER");

        userRepository.save(user);

        Worker worker = new Worker();

        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setPhone(request.getPhone());
        worker.setCategory(request.getCategory());
        worker.setCity(request.getCity());
        worker.setExperience(request.getExperience());

        worker.setPricePerHour(
                request.getPricePerHour()
        );

        worker.setStatus("ACTIVE");
        worker.setAvailable(true);
        worker.setImageUrl("");

        return workerRepository.save(worker);
    }

    public LoginResponse login(
            LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        Long workerId = null;

        if ("WORKER".equalsIgnoreCase(
                user.getRole())) {

            Worker worker = workerRepository
                    .findByEmail(user.getEmail())
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Worker profile not found"
                            )
                    );

            workerId = worker.getId();
        }

        return new LoginResponse(
                token,
                "Login Successful",
                user.getId(),
                workerId,
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
package com.helperhub.controller;

import com.helperhub.dto.LoginRequest;
import com.helperhub.dto.LoginResponse;
import com.helperhub.dto.RegisterRequest;
import com.helperhub.dto.WorkerRegisterRequest;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/worker/register")
    public Worker registerWorker(
            @RequestBody WorkerRegisterRequest request) {

        return authService.registerWorker(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
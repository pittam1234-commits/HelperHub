package com.helperhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {

        Map<String, String> response = new HashMap<>();

        response.put("status", "SUCCESS");
        response.put("message", "HelperHub Backend API is Running");
        response.put("swagger", "/swagger-ui/index.html");

        return response;
    }
}

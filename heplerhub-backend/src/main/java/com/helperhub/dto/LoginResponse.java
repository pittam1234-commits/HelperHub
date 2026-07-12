package com.helperhub.dto;

public class LoginResponse {

    private String token;
    private String message;
    private Long userId;
    private Long workerId;
    private String name;
    private String email;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            String message,
            Long userId,
            Long workerId,
            String name,
            String email,
            String role) {

        this.token = token;
        this.message = message;
        this.userId = userId;
        this.workerId = workerId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
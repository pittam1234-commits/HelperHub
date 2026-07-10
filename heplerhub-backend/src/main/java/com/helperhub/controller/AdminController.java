package com.helperhub.controller;

import com.helperhub.dto.DashboardResponse;
import com.helperhub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return adminService.getDashboard();
    }
}
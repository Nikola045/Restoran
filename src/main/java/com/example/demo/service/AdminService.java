package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String username, String password) {
        Admin admin = adminRepository.getByUsername(username);
        if(admin == null || !admin.getPassword().equals(password))
            return null;
        return  admin;
    }
}

package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RestoranRepository restoranRepository;

    public Admin login(String username, String password) {
        Admin admin = adminRepository.getByUsername(username);
        if(admin == null || !admin.getPassword().equals(password))
            return null;
        return  admin;
    }

    public void obrisiRestoran(String naziv){
        Set<Restoran> restorani =  new HashSet<>();
        for(Restoran restoran:restorani) {
            if (restoran.getNaziv().equals(naziv)) {
                restoranRepository.delete(restoran);
            }
        }
        }
}


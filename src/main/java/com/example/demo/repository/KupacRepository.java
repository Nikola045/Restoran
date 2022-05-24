package com.example.demo.repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KupacRepository extends JpaRepository<Kupac,String> {
    Kupac getByUsername(String username);
}

package com.example.demo.repository;

import com.example.demo.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenadzerRepository extends JpaRepository<Menadzer,String> {

    Menadzer getByUsername(String username);

}

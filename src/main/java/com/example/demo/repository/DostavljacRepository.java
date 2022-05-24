package com.example.demo.repository;

import com.example.demo.entity.Dostavljac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DostavljacRepository extends JpaRepository<Dostavljac,String> {

    Dostavljac getByUsername(String username);
}

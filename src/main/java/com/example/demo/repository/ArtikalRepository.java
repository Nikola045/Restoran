package com.example.demo.repository;

import com.example.demo.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
    Artikal getById(Long id);
}

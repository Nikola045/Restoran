package com.example.demo.repository;

import com.example.demo.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
}

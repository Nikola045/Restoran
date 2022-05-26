package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LokacijaRepository extends JpaRepository<Lokacija,Long> {
    Lokacija getByAdresa(String adresa);
}

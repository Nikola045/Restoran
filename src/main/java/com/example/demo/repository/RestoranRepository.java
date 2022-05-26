package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestoranRepository extends JpaRepository<Restoran,Long> {
    Restoran getById(Long id);

     Restoran getByNaziv(String nazivRestorana);
    // Restoran getByLokacija(Lokacija lokacija);
}

package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RestoranRepository extends JpaRepository<Restoran,String> {

     Restoran getByNaziv(String nazivRestorana);
    Restoran getByTipRestorana(@Param("tipRestorana") String tipRestorana);
    Restoran getByLokacija(Lokacija lokacija);
}

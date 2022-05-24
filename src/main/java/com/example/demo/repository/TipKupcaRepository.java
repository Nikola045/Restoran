package com.example.demo.repository;

import com.example.demo.entity.TipKupac;
import com.example.demo.entity.VrstaPotrosaca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipKupcaRepository extends JpaRepository<TipKupac, Long> {
}

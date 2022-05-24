package com.example.demo.repository;

import com.example.demo.entity.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa,String> {

}

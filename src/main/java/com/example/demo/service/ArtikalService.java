package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal nadjiPoId(Long id)
    {
        Artikal artikal = new Artikal();
        artikal = artikalRepository.getById(id);
        return artikal;
    }
}

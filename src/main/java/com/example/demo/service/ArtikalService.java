package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private RestoranRepository restoranRepository;



    public Artikal napraviArtikal(Menadzer menadzer, String naziv, int cena, String tipArtikla)
    {
        Artikal artikal = new Artikal();
        artikal.setNazivArtikla(naziv);
        artikal.setCena(cena);
        artikal.setTipArtikla(tipArtikla);
        Restoran restoran = menadzer.getZaduzenRestoran();

        Set<Artikal> noviArtikli = new HashSet<>(restoran.getArtikli());
        noviArtikli.add(artikal);
        artikal.setRestoran(restoran);
        restoran.setArtikli(noviArtikli);
        artikalRepository.save(artikal);
        restoranRepository.save(restoran);


        return artikal;
    }
}

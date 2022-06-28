package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private RestoranService restoranService;

    public String dodajMenadzera(Menadzer menadzer) {
        menadzerRepository.save(menadzer);
        return "Uspesno dodat menadzer!";
    }

    public Menadzer login(String username, String sifra) {
        Menadzer menadzer = menadzerRepository.getByUsername(username);
        if (menadzer == null || !menadzer.getPassword().equals(sifra)) {
            return null;
        }

        return menadzer;
    }

    public boolean PromeniStatus(Porudzbina porudzbina, Status status) {
        if (status != Status.U_PRIPREMI || status != Status.CEKA_DOSTAVLJACA) {
            return false;
        } else {
            porudzbina.setTrenutnoStanjePorudzbine(status);
        }

        return true;
    }

    public List<Menadzer> findAll() {
        return menadzerRepository.findAll();
    }

    public Menadzer postaviRestoran(String username, String nazivRestorana) {
        Menadzer menadzer = menadzerRepository.getByUsername(username);
        Restoran restoran = restoranService.nadjiPoImenu(nazivRestorana);
        if (restoran == null)
            return null;
        menadzer.setZaduzenRestoran(restoran);
        return menadzerRepository.save(menadzer);
    }

    public Menadzer findOne(String id) {
        Optional<Menadzer> foundEmployee = menadzerRepository.findById(id);
        return foundEmployee.orElse(null);
    }

    public void obrisiArtikal(String id, Menadzer menadzer) {
        Set<Artikal> artikli = menadzer.getZaduzenRestoran().getArtikli();
        for (Artikal artikal : artikli) {
            if (artikal.getNazivArtikla().equals(id)) {
                menadzer.getZaduzenRestoran().getArtikli().remove(artikal);
                artikalRepository.delete(artikal);
            }
        }
    }

    public Artikal izabraniArtikal(String id, Menadzer menadzer) {
        Set<Artikal> artikli = menadzer.getZaduzenRestoran().getArtikli();
        for (Artikal artikal : artikli) {
            if (artikal.getNazivArtikla().equals(id)) {
                return artikal;
            }
        }
        return null;
    }

    public Set<KorisnikDto> findbyIme(String ime) {
        Set<Menadzer> menadzeri = new HashSet<>(menadzerRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for(Menadzer menadzer : menadzeri){
            if(menadzer.getIme().equals(ime)){
                KorisnikDto dto = new KorisnikDto(menadzer);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public Set<KorisnikDto> findbyPrezime(String prezime) {
        Set<Menadzer> menadzeri = new HashSet<>(menadzerRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for(Menadzer menadzer : menadzeri){
            if(menadzer.getPrezime().equals(prezime)){
                KorisnikDto dto = new KorisnikDto(menadzer);
                dtos.add(dto);
            }
        }
        return dtos;
    }
    public Set<KorisnikDto> findbyUsername(String username) {
        Set<Menadzer> menadzeri = new HashSet<>(menadzerRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for(Menadzer menadzer : menadzeri){
            if(menadzer.getUsername().equals(username)){
                KorisnikDto dto = new KorisnikDto(menadzer);
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
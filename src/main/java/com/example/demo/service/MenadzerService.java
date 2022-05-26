package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String dodajMenadzera(Menadzer menadzer)
    {
        menadzerRepository.save(menadzer);
        return "Uspesno dodat menadzer!";
    }
    public Menadzer login(String username, String sifra)
    {
        Menadzer menadzer = menadzerRepository.getByUsername(username);
        if(menadzer==null || !menadzer.getPassword().equals(sifra))
        {
            return null;
        }

        return menadzer;
    }

    public boolean PromeniStatus(Porudzbina porudzbina, Status status)
    {
        if(status!=Status.U_PRIPREMI || status!=Status.CEKA_DOSTAVLJACA)
        {
            return false;
        }

        else
        {
            porudzbina.setTrenutnoStanjePorudzbine(status);
        }

        return true;
    }
    public List<Menadzer> findAll(){
        return menadzerRepository.findAll();
    }

    public Menadzer postaviRestoran(String username, Long Id) {
        Menadzer menadzer = menadzerRepository.getByUsername(username);
        Restoran restoran= restoranService.findById(Id);
        if(restoran == null)
            return null;
        menadzer.setZaduzenRestoran(restoran);
        return menadzerRepository.save(menadzer);
    }

    public Menadzer findOne(String id){
        Optional<Menadzer> foundEmployee = menadzerRepository.findById(id);
        return foundEmployee.orElse(null);
    }

    public void obrisiArtikal(String id,Menadzer menadzer){
        Set<Artikal> artikli =  menadzer.getZaduzenRestoran().getArtikli();
        for(Artikal artikal : artikli){
            if(artikal.getIdArtikal().equals(id)){
                menadzer.getZaduzenRestoran().getArtikli().remove(artikal);
                artikalRepository.delete(artikal);
            }
        }
    }
}

package com.example.demo.service;

import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Status;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

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

}

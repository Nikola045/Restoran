package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KupacRepository kupacRepository;


    public Set<Porudzbina> pregledajPorudzbineSlobodne(Status status)
    {
        Set<Porudzbina> porudzbine = (Set<Porudzbina>) porudzbinaRepository.findAll();
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            if(p.getTrenutnoStanjePorudzbine()==p.getTrenutnoStanjePorudzbine())
            {
                vrati.add(p);
            }
        }
        return vrati;
    }

    public Set<Porudzbina> porudzbineRestorana(Menadzer menadzer)
    {
        Set<Porudzbina> porudzbine = (Set<Porudzbina>) porudzbinaRepository.findAll();
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p:porudzbine)
        {
            if(p.getRestoranPoruceno()==menadzer.getZaduzenRestoran())
            {
                vrati.add(p);
            }
        }
        return vrati;
    }

    public void Poruci(Porudzbina porudzbina)
    {
        porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina poruci(Kupac kupac, Restoran restoran)
    {
        Porudzbina porudzbina = new Porudzbina();
        Korpa korpa = kupac.getKorpa();

        porudzbina.setPoruceniArtikli(korpa.getArtikli());
        porudzbina.setVremePoruzbine(new Date());
        porudzbina.setKupacIme(kupac.getIme());
        porudzbina.setTrenutnoStanjePorudzbine(Status.OBRADA);
        porudzbina.setRestoranPoruceno(restoran);


        int brBod = (int) korpa.getUkupnaCena();
        kupac.setBrojSkupljenihBodova((brBod/1000)*133);

        porudzbinaRepository.save(porudzbina);

        Set<Porudzbina> porudzbinas = kupac.getPorudzbine();
        porudzbinas.add(porudzbina);
        kupac.setPorudzbine(porudzbinas);

        kupac.setKorpa(null);
        kupacRepository.save(kupac);

        return porudzbina;
    }





}

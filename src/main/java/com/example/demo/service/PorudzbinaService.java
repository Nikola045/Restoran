package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;


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

    public void dodajPorudzbinu(Porudzbina porudzbina)
    {
        porudzbinaRepository.save(porudzbina);
    }

    public void Poruci(Porudzbina porudzbina)
    {
        porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina poruci(Kupac kupac, Restoran restoran)
    {
        Porudzbina porudzbina = new Porudzbina();
        Korpa korpa = kupac.getKorpa();

        porudzbina.setPoruceniArtikli(korpa.getPoruceniArtikli());
        porudzbina.setVremePoruzbine(new Date());
        porudzbina.setKupacIme(kupac.getIme());
        porudzbina.setTrenutnoStanjePorudzbine(Status.OBRADA);
        porudzbina.setRestoranPoruceno(restoran);


        kupac.dodajBodove(korpa.ukupnaCena());

        porudzbinaRepository.save(porudzbina);

        Set<Porudzbina> porudzbinas = kupac.getPorudzbine();
        porudzbinas.add(porudzbina);
        kupac.setPorudzbine(porudzbinas);

        kupac.setKorpa(null);
        kupacRepository.save(kupac);

        return porudzbina;
    }


    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }
}

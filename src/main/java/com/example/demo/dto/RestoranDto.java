package com.example.demo.dto;

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class RestoranDto {

    private String naziv;
    private String tipRestorana;
    private Menadzer menadzer;
    private Porudzbina porudzbina;
    private Set<Artikal> artikli = new HashSet<>();
    private Lokacija lokacija;

    public RestoranDto(String naziv, String tipRestorana, Menadzer menadzer, Porudzbina porudzbina, Set<Artikal> artikli, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.menadzer = menadzer;
        this.porudzbina = porudzbina;
        this.artikli = artikli;
        this.lokacija = lokacija;
    }

    public RestoranDto() {

    }

    public RestoranDto(Restoran restoran) {
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.menadzer = restoran.getMenadzer();
        this.porudzbina = restoran.getPorudzbina();
        this.artikli = restoran.getArtikli();
        this.lokacija = restoran.getLokacija();
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}


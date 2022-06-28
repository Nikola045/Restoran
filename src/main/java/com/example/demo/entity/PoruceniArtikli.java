package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class PoruceniArtikli
{
    @Id
    private String nazivArtikla;

    @ManyToOne
    @JsonIgnore
    private Artikal artikal;

    @ManyToOne
    @JsonIgnore
    private Porudzbina porudzbina;

    @Column
    private int kolicina;

    @Column
    private double ukupnaCena;

    @ManyToOne
    @JsonIgnore
    private Korpa korpa;


    public PoruceniArtikli() {
    }

    public PoruceniArtikli(String nazivArtikla, Artikal artikal, Porudzbina porudzbina, int kolicina, double ukupnaCena) {
        this.nazivArtikla = nazivArtikla;
        this.artikal = artikal;
        this.porudzbina = porudzbina;
        this.kolicina = kolicina;
        this.ukupnaCena = ukupnaCena;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}

package com.example.demo.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Restoran {
    @Id
    private String naziv;
    @Column
    private String tipRestorana;

    @OneToOne
    private Menadzer menadzer;

    @OneToMany(mappedBy = "restoranPoruceno")

    private Set<Porudzbina> porudzbine;

    @OneToMany(mappedBy = "restoran", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @OneToMany(mappedBy = "restoran",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Artikal> artikli = new HashSet<>();

    @OneToOne(mappedBy = "restoran")
    private Lokacija lokacija;

    public Restoran(String naziv, String tipRestorana) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
    }

    public Restoran(String naziv, String tipRestorana, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public Restoran() {

    }

    public Restoran(Restoran odabrani) {
        this.naziv = odabrani.getNaziv();
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

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbina) {
        this.porudzbine = porudzbina;
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

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }
}

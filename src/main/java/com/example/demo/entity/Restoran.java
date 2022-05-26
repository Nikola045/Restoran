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
    private Long id;
    @Column
    private String naziv;
    @Column
    private String tipRestorana;

    @OneToOne
    private Menadzer menadzer;

    @OneToOne(mappedBy = "restoranPoruceno")
    @JsonIgnore
    private Porudzbina porudzbina;

    @OneToMany(mappedBy = "restoran", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @OneToMany(mappedBy = "restoran",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Artikal> artikli = new HashSet<>();

    @OneToOne(mappedBy = "restoran")
    private Lokacija lokacija;

    public Restoran(Long id, String naziv, String tipRestorana) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }
}

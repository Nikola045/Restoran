package com.example.demo.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Kupac extends Korisnik{


    @Column
    private int brojSkupljenihBodova;

    @OneToMany(mappedBy = "kupac",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @ManyToOne
    private TipKupac tipKupca;



    @OneToMany(mappedBy = "kupac",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    public Kupac(String username, String password, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, int brojSkupljenihBodova ) {
        super(username, password, ime, prezime, pol, datumRodjenja, uloga);
        this.brojSkupljenihBodova = brojSkupljenihBodova;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    @OneToOne(mappedBy = "kupac",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Korpa korpa;

    public Kupac() {

    }

    public int getBrojSkupljenihBodova() {
        return brojSkupljenihBodova;
    }

    public void setBrojSkupljenihBodova(int brojSkupljenihBodova) {
        this.brojSkupljenihBodova = brojSkupljenihBodova;
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public TipKupac getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupac tipKupca) {
        this.tipKupca = tipKupca;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }
}

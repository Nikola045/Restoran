package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Artikal {

    @Id
    private String idArtikal;
    @Column
    private String nazivArtikla;
    @Column
    private int cena;
    @Column
    @JsonIgnore
    private String tipArtikla;
    @Column
    @JsonIgnore
    private String opis;
    @Column
    private int kolicina;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Porudzbina porudzbina;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Restoran restoran;
    @ManyToOne
    private Korpa korpa;

    public Artikal(String idArtikal, String nazivArtikla, int cena, String opis, int kolicina) {
        this.idArtikal = idArtikal;
        this.nazivArtikla = nazivArtikla;
        this.cena = cena;
        this.opis = opis;
        this.kolicina = kolicina;
    }

    public Artikal() {

    }

    public String getIdArtikal() {
        return idArtikal;
    }

    public void setIdArtikal(String idArtikal) {
        this.idArtikal = idArtikal;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(String tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}

package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Porudzbina {
    @Id
    private Long idPorudzbine;
    @Column
    private Date vremePoruzbine = new Date();
    @Column
    private int cenaPorudzbine;
    @Enumerated(EnumType.STRING)
    @Column
    private Status trenutnoStanjePorudzbine;



    @Column
    private String kupacIme;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dostavljac dostavljac;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @ManyToOne
    private Restoran restoranPoruceno;

    public Porudzbina(Long idPorudzbine, Date vremePoruzbine, int cenaPorudzbine, Status trenutnoStanjePorudzbine) {
        this.idPorudzbine = idPorudzbine;
        this.vremePoruzbine = vremePoruzbine;
        this.cenaPorudzbine = cenaPorudzbine;
        this.trenutnoStanjePorudzbine = trenutnoStanjePorudzbine;
    }


    public Porudzbina() {

    }

    public Long getIdPorudzbine() {
        return idPorudzbine;
    }

    public void setIdPorudzbine(Long idPorudzbine) {
        this.idPorudzbine = idPorudzbine;
    }

    public Date getVremePoruzbine() {
        return vremePoruzbine;
    }

    public void setVremePoruzbine(Date vremePoruzbine) {
        this.vremePoruzbine = vremePoruzbine;
    }

    public int getCenaPorudzbine() {
        return cenaPorudzbine;
    }

    public void setCenaPorudzbine(int cenaPorudzbine) {
        this.cenaPorudzbine = cenaPorudzbine;
    }

    public Status getTrenutnoStanjePorudzbine() {
        return trenutnoStanjePorudzbine;
    }

    public void setTrenutnoStanjePorudzbine(Status trenutnoStanjePorudzbine) {
        this.trenutnoStanjePorudzbine = trenutnoStanjePorudzbine;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    public Set<PoruceniArtikli> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<PoruceniArtikli> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoranPoruceno() {
        return restoranPoruceno;
    }

    public void setRestoranPoruceno(Restoran restoranPoruceno) {
        this.restoranPoruceno = restoranPoruceno;
    }

    public String getKupacIme() {
        return kupacIme;
    }

    public void setKupacIme(String kupacIme) {
        this.kupacIme = kupacIme;
    }
}

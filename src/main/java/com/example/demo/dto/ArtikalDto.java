package com.example.demo.dto;

import com.example.demo.entity.Korpa;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class ArtikalDto {

    private String nazivArtikla;
    private int cena;
    private String tipArtikla;
    private Restoran restoran;


    public ArtikalDto( String nazivArtikla, int cena, String tipArtikla) {

        this.nazivArtikla = nazivArtikla;
        this.cena = cena;
        this.tipArtikla = tipArtikla;

    }

    public ArtikalDto() {

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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}

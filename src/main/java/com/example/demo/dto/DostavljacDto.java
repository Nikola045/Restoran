package com.example.demo.dto;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Uloga;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DostavljacDto {
    protected String username;
    protected String password;
    protected String ime;
    protected String prezime;
    protected String pol;
    protected Date datumRodjenja = new Date();
    protected Uloga uloga;
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public DostavljacDto(String username, String password, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> porudzbine) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
        this.porudzbine = porudzbine;
    }

    public DostavljacDto() {

    }

    public DostavljacDto(Dostavljac dostavljac) {
        this.username = dostavljac.getUsername();
        this.password = dostavljac.getPassword();
        this.ime = dostavljac.getIme();
        this.prezime = dostavljac.getPrezime();
        this.pol = dostavljac.getPol();
        this.datumRodjenja = dostavljac.getDatumRodjenja();
        this.uloga = dostavljac.getUloga();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }
}


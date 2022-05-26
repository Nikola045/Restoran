package com.example.demo.dto;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Uloga;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KupacDto {
    protected String username;
    protected String password;
    protected String ime;
    protected String prezime;
    protected String pol;
    protected Date datumRodjenja = new Date();
    protected Uloga uloga;
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public KupacDto(String username, String password, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga,Set<Porudzbina> porudzbine) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
        this.porudzbine = porudzbine;
    }

    public KupacDto() {

    }

    public KupacDto(Kupac kupac) {
        this.username = kupac.getUsername();
        this.password = kupac.getPassword();
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.pol = kupac.getPol();
        this.datumRodjenja = kupac.getDatumRodjenja();
        this.uloga = kupac.getUloga();
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
}

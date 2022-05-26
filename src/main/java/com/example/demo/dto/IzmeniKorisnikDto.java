package com.example.demo.dto;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Uloga;

import java.util.Date;

public class IzmeniKorisnikDto {

    protected String username;
    protected String password;
    protected String ime;
    protected String prezime;


    public IzmeniKorisnikDto(String username, String password, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;

    }

    public IzmeniKorisnikDto() {

    }

    public IzmeniKorisnikDto(Kupac kupac) {
        this.username = kupac.getUsername();
        this.password = kupac.getPassword();
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
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
}

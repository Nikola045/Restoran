package com.example.demo.dto;

import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Uloga;

import java.util.Date;

public class MenadzerDto {

        protected String username;
        protected String password;
        protected String ime;
        protected String prezime;
        protected String pol;
        protected Date datumRodjenja = new Date();
        protected Uloga uloga;

    public Restoran getZaduzenRestoran() {
        return zaduzenRestoran;
    }

    public void setZaduzenRestoran(Restoran zaduzenRestoran) {
        this.zaduzenRestoran = zaduzenRestoran;
    }

    private Restoran zaduzenRestoran;

        public MenadzerDto(String username, String password, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga,Restoran zaduzenRestoran) {
            this.username = username;
            this.password = password;
            this.ime = ime;
            this.prezime = prezime;
            this.pol = pol;
            this.datumRodjenja = datumRodjenja;
            this.uloga = uloga;
            this.zaduzenRestoran = zaduzenRestoran;
        }

        public MenadzerDto() {

        }

        public MenadzerDto(Menadzer menadzer) {
            this.username = menadzer.getUsername();
            this.password = menadzer.getPassword();
            this.ime = menadzer.getIme();
            this.prezime = menadzer.getPrezime();
            this.pol = menadzer.getPol();
            this.datumRodjenja = menadzer.getDatumRodjenja();
            this.uloga = menadzer.getUloga();
            this.zaduzenRestoran = menadzer.getZaduzenRestoran();
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


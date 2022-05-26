package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
public class Menadzer extends Korisnik{

    @OneToOne(mappedBy = "menadzer")
    @JsonIgnore
    private Restoran zaduzenRestoran;

    public Menadzer(String username, String password, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga) {
        super(username, password, ime, prezime, pol, datumRodjenja, uloga);

    }

    public Menadzer(String username, String password) {
        super(username, password);
    }

    public Menadzer() {

    }

    public Restoran getZaduzenRestoran() {
        return zaduzenRestoran;
    }

    public void setZaduzenRestoran(Restoran zaduzenRestoran) {
        this.zaduzenRestoran = zaduzenRestoran;
    }
}

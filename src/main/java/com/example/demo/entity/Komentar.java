package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKomentar;
    @Column
    private String tekstKomentara;
    @Column
    private int ocena;

    @ManyToOne
    private Kupac kupac;

    @ManyToOne
    private Restoran restoran;

    public Komentar(Long idKomentar, String tekstKomentara, int ocena) {
        this.idKomentar = idKomentar;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    public Komentar() {

    }

    public Long getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(Long idKomentar) {
        this.idKomentar = idKomentar;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}

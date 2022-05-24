package com.example.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipKupac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipKupca;
    @Column
    private VrstaPotrosaca ime;
    @Column
    private double popust;
    @Column
    private int trazeniBrojBodova;



    @OneToMany
    private Set<Kupac> kupci = new HashSet<>();

    public TipKupac(Long idTipKupca, VrstaPotrosaca ime, double popust, int trazeniBrojBodova) {
        this.idTipKupca = idTipKupca;
        this.ime = ime;
        this.popust = popust;
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    public TipKupac() {

    }

    public Long getIdTipKupca() {
        return idTipKupca;
    }

    public void setIdTipKupca(Long idTipKupca) {
        this.idTipKupca = idTipKupca;
    }

    public VrstaPotrosaca getIme() {
        return ime;
    }

    public void setIme(VrstaPotrosaca ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getTrazeniBrojBodova() {
        return trazeniBrojBodova;
    }

    public void setTrazeniBrojBodova(int trazeniBrojBodova) {
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    public Set<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(Set<Kupac> kupci) {
        this.kupci = kupci;
    }
}

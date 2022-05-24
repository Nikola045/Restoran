package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String adresa;
    @Column
    private int geografskaDuzina;
    @Column
    private int geografskaSirina;

    @OneToOne
    private Restoran restoran;

    public Lokacija(Long id, String adresa, int geografskaDuzina, int geografskaSirina) {
        this.id = id;
        this.adresa = adresa;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;

    }

    public Lokacija() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(int geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public int getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(int geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}

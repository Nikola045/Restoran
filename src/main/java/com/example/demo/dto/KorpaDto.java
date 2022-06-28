package com.example.demo.dto;

public class KorpaDto
{
    private String nazivArtikla;
    private int kolicina;
    private String nazivRestorana;


    public KorpaDto(String nazivArtikla, int kolicina, String nazivRestorana)
    {
        this.nazivArtikla = nazivArtikla;
        this.kolicina = kolicina;
        this.nazivRestorana = nazivRestorana;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public KorpaDto() {
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}

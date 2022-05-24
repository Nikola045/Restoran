package com.example.demo.dto;

public class KorpaDto {
    private Long IDartikal;
    private int kolicina;
    private String nazivRestorana;


    public KorpaDto(Long IDartikal, int kolicina, String nazivRestorana)
    {
        this.IDartikal = IDartikal;
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

    public Long getIDartikal() {
        return IDartikal;
    }

    public void setIDartikal(Long IDartikal) {
        this.IDartikal = IDartikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}

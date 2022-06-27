package com.example.demo.service;

import com.example.demo.dto.KorpaDto;
import com.example.demo.entity.Artikal;
import com.example.demo.entity.Korpa;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.PoruceniArtikli;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.KorpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KorpaService {
    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    public boolean dodajUKorpu(Kupac kupac, KorpaDto korpaDto) {

        if (kupac.getKorpa()==null)
        {
            Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();

            Artikal artikal = new Artikal();
            artikal = artikalRepository.getByNazivArtikla(korpaDto.getNazivArtikla());

            PoruceniArtikli pa = new PoruceniArtikli();
            pa.setArtikal(artikal);
            pa.setKolicina(korpaDto.getKolicina());
            pa.setNazivArtikla(korpaDto.getNazivArtikla());
            pa.setUkupnaCena(artikal.getCena()*korpaDto.getKolicina());

            poruceniArtikli.add(pa);

            Korpa korpa = new Korpa(kupac,poruceniArtikli);

            kupac.setKorpa(korpa);
            korpaRepository.save(korpa);
            return true;
        }

        else
        {
            Korpa k = kupac.getKorpa();

            Set<PoruceniArtikli> poruceniArtikli = new HashSet<>();
            poruceniArtikli = k.getPoruceniArtikli();


            Artikal artikal = new Artikal();
            artikal = artikalRepository.getByNazivArtikla(korpaDto.getNazivArtikla());

            PoruceniArtikli pa = new PoruceniArtikli();
            pa.setArtikal(artikal);
            pa.setKolicina(korpaDto.getKolicina());
            pa.setNazivArtikla(korpaDto.getNazivArtikla());
            pa.setUkupnaCena(artikal.getCena()*korpaDto.getKolicina());

            poruceniArtikli.add(pa);

            k.setPoruceniArtikli(poruceniArtikli);
            korpaRepository.save(k);
            return true;
        }
    }

    public Korpa pregledajKorpu(Kupac kupac)
    {
        return kupac.getKorpa();
    }

}

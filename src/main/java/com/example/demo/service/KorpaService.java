package com.example.demo.service;

import com.example.demo.dto.KorpaDto;
import com.example.demo.entity.Artikal;
import com.example.demo.entity.Korpa;
import com.example.demo.entity.Kupac;
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
            Set<Artikal> artikals = new HashSet<>();

            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            artikal.setKolicina(korpaDto.getKolicina());
            double ukupnaCena = korpaDto.getKolicina()*artikal.getCena();

            artikals.add(artikal);

            Korpa korpa = new Korpa(kupac,artikals,ukupnaCena);

            kupac.setKorpa(korpa);
            korpaRepository.save(korpa);
            return true;
        }

        else
        {
            Korpa k = kupac.getKorpa();
            double ukupnaCena = k.getUkupnaCena();


            for (Artikal artikal:k.getArtikli())
            {
                if(korpaDto.getIDartikal().equals(artikal.getNazivArtikla()))
                {
                    Set<Artikal> artikals = new HashSet<>();
                    artikals = k.getArtikli();
                    int kol = artikal.getKolicina();
                    artikals.remove(artikal);

                    Artikal artikall = new Artikal();
                    artikall = artikalRepository.getById(korpaDto.getIDartikal());

                    artikall.setKolicina(korpaDto.getKolicina()+kol);
                    ukupnaCena-=kol*artikall.getCena();
                    ukupnaCena+=artikall.getCena()*(korpaDto.getKolicina()+kol);

                    artikals.add(artikall);

                    k.setArtikli(artikals);
                    k.setUkupnaCena(ukupnaCena);
                    korpaRepository.save(k);

                    return true;
                }
            }


            Set<Artikal> artikals = new HashSet<>();

            artikals = k.getArtikli();


            Artikal artikal = new Artikal();
            artikal = artikalRepository.getById(korpaDto.getIDartikal());

            artikal.setKolicina(korpaDto.getKolicina());
            ukupnaCena+=artikal.getCena()*korpaDto.getKolicina();

            artikals.add(artikal);

            k.setArtikli(artikals);
            k.setUkupnaCena(ukupnaCena);
            korpaRepository.save(k);
            return true;
        }
    }

    public Korpa pregledajKorpu(Kupac kupac)
    {
        return kupac.getKorpa();
    }
}

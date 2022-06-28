package com.example.demo.service;

import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;


    public Restoran nadjiPoImenu(String nazivRestorana)
    {
        Restoran restoran = (Restoran) restoranRepository.getByNaziv(nazivRestorana);
        return restoran;
    }

   /* public Restoran nadjiPoLokaciji(Lokacija lokacija)
    {
        Restoran restoran = (Restoran) restoranRepository.getByLokacija(lokacija);
        return restoran;
    }*/

    public String dodajRestoran(Restoran restoran)
    {
        restoranRepository.save(restoran);
        return "Uspesno dodat restoran!";
    }

    public List<Restoran> findAll(){
        return restoranRepository.findAll();
    }

    public Restoran findOne(String naziv){
        Restoran foundRestoran = restoranRepository.getByNaziv(naziv);
        return foundRestoran;
    }

    public RestoranDto findOneRestoran(Restoran odabrani) {
        RestoranDto restoranDto = new RestoranDto(odabrani);
        return restoranDto;
    }
}

package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;

    public Kupac save(Kupac kupac)
    {
        return kupacRepository.save(kupac);
    }

    public Kupac login(String username,String password)
    {
        Kupac kupac = kupacRepository.getByUsername(username);
        if(kupac==null || !kupac.getPassword().equals(password))
        {
            return null;
        }

        return kupac;
    }

    public Set<Porudzbina> pregledajPorudzbine(String username)
    {
        Kupac kupac = kupacRepository.getById(username);

        return kupac.getPorudzbine();
    }

    public Kupac findOne(String id){
        Optional<Kupac> foundEmployee = kupacRepository.findById(id);
        return foundEmployee.orElse(null);
    }


    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }


    public Set<KorisnikDto> findByIme(String ime) {
        Set<Kupac> kupci = new HashSet<>(kupacRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for (Kupac kupac : kupci) {
            if (kupac.getIme().equals(ime)) {
                KorisnikDto dto = new KorisnikDto(kupac);
                dtos.add(dto);
            }
        }
        return dtos;
    }
    public Set<KorisnikDto> findByPrezime(String prezime) {
        Set<Kupac> kupci = new HashSet<>(kupacRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for (Kupac kupac : kupci) {
            if (kupac.getPrezime().equals(prezime)) {
                KorisnikDto dto = new KorisnikDto(kupac);
                dtos.add(dto);
            }
        }
        return dtos;
    }
    public Set<KorisnikDto> findByUsername(String username) {
        Set<Kupac> kupci = new HashSet<>(kupacRepository.findAll());
        Set<KorisnikDto> dtos = new HashSet<>();
        for (Kupac kupac : kupci) {
            if (kupac.getUsername().equals(username)) {
                KorisnikDto dto = new KorisnikDto(kupac);
                dtos.add(dto);
            }
        }
        return dtos;
    }

}

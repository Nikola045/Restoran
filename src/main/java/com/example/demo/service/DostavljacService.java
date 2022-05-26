package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DostavljacService {
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Dostavljac login(String username, String password) {
        Dostavljac dostavljac = dostavljacRepository.getByUsername(username);
        if(dostavljac == null || !dostavljac.getPassword().equals(password))
            return null;
        return  dostavljac;
    }

    public Dostavljac findOne(String id){
        Optional<Dostavljac> foundEmployee = dostavljacRepository.findById(id);
        return foundEmployee.orElse(null);
    }

    public Set<Porudzbina> pregledajPorudzbineZaduzen(String username)
    {
        Dostavljac dostavljac = dostavljacRepository.getById(username);

        return dostavljac.getPorudzbine();
    }

    public String dodajDostavljaca(Dostavljac dostavljac)
    {
        dostavljacRepository.save(dostavljac);
        return "Uspesno dodat dostavljac!";
    }

    public List<Dostavljac> findAll(){
        return dostavljacRepository.findAll();
    }

}
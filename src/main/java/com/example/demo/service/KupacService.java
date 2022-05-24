package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


}

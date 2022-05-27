package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.KupacDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class RestoranRestController {

    @Autowired
    RestoranService restoranService;
    @Autowired
    RestoranRepository restoranRepository;
    @Autowired
    LokacijaRepository lokacijaRepository;

    @GetMapping("/api/restoran/pregled")
    public ResponseEntity<List<RestoranDto>> getRestorani()
    {
        List<Restoran> restoranList = restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran restoran : restoranList){
            RestoranDto dto = new RestoranDto(restoran);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/api/restoran/pretragaNaziv")
    public ResponseEntity<RestoranDto> getRestoranPoNazivu(@RequestParam String naziv){

        Restoran restoran = restoranService.nadjiPoImenu(naziv);
        RestoranDto dto = new RestoranDto(restoran);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/restoran/pretragaTip")
    public ResponseEntity pretraziRestoranPoTipu(@RequestParam String tipRestorana, HttpSession session) {

        if (tipRestorana == null || tipRestorana.isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByTipRestorana(tipRestorana);

        if (restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }


    @GetMapping("/api/restoran/pretragaLokacija")
    public ResponseEntity pretraziRestoranPoLokaciji(@RequestParam String adresa, HttpSession session) {

        Lokacija lokacija = lokacijaRepository.getByAdresa(adresa);

        if(lokacija == null || lokacija.toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByLokacija(lokacija);

        if (restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }


    @GetMapping("/api/restoran/info")
    public Restoran ispisiRestoran(@RequestParam String naziv) {
        List<Restoran> restoranList = restoranRepository.findAll();

        for (Restoran r : restoranList)
            if (Objects.equals(naziv, r.getNaziv()))
            return r;

        return null;
    }






}

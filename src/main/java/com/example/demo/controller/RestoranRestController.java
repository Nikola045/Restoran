package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.KupacDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class RestoranRestController {

    @Autowired
    RestoranService restoranService;

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

//ne radi
    @GetMapping("/api/restoran/izabrani/{odabrani}")
    public ResponseEntity<RestoranDto> getRestoran(@RequestParam Restoran odabrani){
       RestoranDto izabraniRestoran = restoranService.findOneRestoran(odabrani);
        return ResponseEntity.ok(izabraniRestoran);
    }




}

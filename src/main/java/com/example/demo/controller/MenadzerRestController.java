package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Set;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @PostMapping("/api/menadzer/prijava")
    public ResponseEntity<String> login(@RequestBody LogInDto loginDto, HttpSession session)
    {
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Menadzer logovaniMenadzer = menadzerService.login(loginDto.getUsername(),loginDto.getPassword());

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("menadzer",logovaniMenadzer);
        return ResponseEntity.ok("Uspesno ulogovan menadzer!");
    }


    @PostMapping("/api/menadzer/odjavi")
    public ResponseEntity logout(HttpSession session)
    {
        Menadzer logovanimenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovanimenadzer == null)
        {
            return new ResponseEntity("Menadzer nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Menadzer uspesno odjavljen iz sistema!",HttpStatus.OK);
    }

    @GetMapping("/api/menadzer/profil")
    public ResponseEntity<MenadzerDto> getMenadzer(HttpSession session){
        Menadzer logovaniMenadzer= (Menadzer) session.getAttribute("menadzer");


        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Menadzer menadzer = menadzerService.findOne(logovaniMenadzer.getUsername());
        MenadzerDto dto = new MenadzerDto(menadzer);
        return ResponseEntity.ok(dto);
    }

//ne radi
    @GetMapping("/api/menadzer/porudzbine")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");

        if(menadzer==null)
        {
            return new ResponseEntity("Menadzer nije prijavljen!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = porudzbinaService.porudzbineRestorana(menadzer);
        return ResponseEntity.ok(porudzbine);
    }


    //ne znam da li radi
    @DeleteMapping("/api/menadzer/brisanjeArtikla")
    public ResponseEntity<String> obrisiArtikal(@RequestParam String artikalId,HttpSession session){
        Menadzer loggedMenadzer = (Menadzer) session.getAttribute("menadzer");
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce izbrisati artikal",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.obrisiArtikal(artikalId,loggedMenadzer);
        return ResponseEntity.ok("Artikal obrisan");

    }

    @PostMapping("/api/menadzer/izmeni")
    public ResponseEntity<Menadzer> setMenadzer(HttpSession session, @RequestBody MenadzerDto menadzerDto) {

        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        logovaniMenadzer.setUsername(menadzerDto.getUsername() == null ? logovaniMenadzer.getUsername() : menadzerDto.getUsername());
        logovaniMenadzer.setPassword(menadzerDto.getPassword() == null ? logovaniMenadzer.getPassword() : menadzerDto.getPassword());
        logovaniMenadzer.setIme(menadzerDto.getIme() == null ? logovaniMenadzer.getIme() : menadzerDto.getIme());
        logovaniMenadzer.setPrezime(menadzerDto.getPrezime() == null ? logovaniMenadzer.getPrezime() : menadzerDto.getPrezime());

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(logovaniMenadzer);
    }

//ne radi
    @PostMapping("/api/menadzer/DodajArtikal")
    public ResponseEntity dodajArtikal(@RequestParam ArtikalDto artikalDto, HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");
        Restoran restoran = (Restoran) session.getAttribute("restoran");//ovo nema bas smisla

        if(logovaniMenadzer == null) {
            return new ResponseEntity("Samo menazder moze da obavi ovu radnju!",HttpStatus.FORBIDDEN);
        }
        if(logovaniMenadzer != restoran.getMenadzer()) {
            return new ResponseEntity("Ovaj menadzer nije zaduzen za ovaj restoran!",HttpStatus.FORBIDDEN);
        }

        Artikal noviArtikal= artikalService.napraviArtikal(artikalDto.getNazivArtikla(),artikalDto.getCena(),artikalDto.getTipArtikla());

        artikalRepository.save(noviArtikal);

        return ResponseEntity.ok(noviArtikal);
    }

}

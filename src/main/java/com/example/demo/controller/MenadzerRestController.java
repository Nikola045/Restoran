package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @PostMapping(value="/api/menadzer/prijava",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogInDto> login(@RequestBody LogInDto loginDto, HttpSession session)
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
        return new ResponseEntity<>(loginDto,HttpStatus.OK);
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

    @GetMapping(value="/api/menadzer/profil",
           produces = MediaType.APPLICATION_JSON_VALUE
    )
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

    @DeleteMapping("/api/menadzer/brisanjeArtikla")
    public ResponseEntity<String> obrisiArtikal(@RequestParam String artikalId,HttpSession session){
        Menadzer loggedMenadzer = (Menadzer) session.getAttribute("menadzer");
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce izbrisati artikal",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.obrisiArtikal(artikalId,loggedMenadzer);
        return ResponseEntity.ok("Artikal obrisan");

    }

    @PostMapping(value="/api/menadzer/izmeni",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
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

    @GetMapping("/api/menadzer/porudzbineRestorana")
    public ResponseEntity<Set<Porudzbina>> getPorudzbineRestorana(HttpSession session) {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");


        if (logovaniMenadzer == null) {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Restoran restoran = logovaniMenadzer.getZaduzenRestoran();

        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        Set<Porudzbina> pronadjenePorudzbine = new HashSet<>();
        for (Porudzbina porudzbinaRestorana : porudzbine) {
            if (porudzbinaRestorana.getRestoranPoruceno() == restoran)
            {
                pronadjenePorudzbine.add(porudzbinaRestorana);
            }

        }
        return ResponseEntity.ok(pronadjenePorudzbine);
    }

    @PostMapping("/api/menadzer/DodajArtikal")
    public ResponseEntity dodajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session)
    {
        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        Restoran restoran = new Restoran(logovaniMenadzer.getZaduzenRestoran());


        if(logovaniMenadzer == null) {
            return new ResponseEntity("Samo menazder moze da obavi ovu radnju!",HttpStatus.FORBIDDEN);
        }
        if(restoran==null){
            return new ResponseEntity("Menadzer nema svoj restoran!",HttpStatus.FORBIDDEN);
        }

        Artikal noviArtikal= artikalService.napraviArtikal(logovaniMenadzer,artikalDto.getNazivArtikla(),artikalDto.getCena(),artikalDto.getTipArtikla());



        return ResponseEntity.ok(noviArtikal);
    }

    @PostMapping("/api/menadzer/izmeniArtikal")
    public ResponseEntity<Artikal> setArtikal(@RequestParam String nazivArtikla, HttpSession session, @RequestBody ArtikalDto artikalDto) {

        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Artikal artikalIzmena = menadzerService.izabraniArtikal(nazivArtikla,logovaniMenadzer);

        artikalIzmena.setNazivArtikla(artikalDto.getNazivArtikla() == null ? artikalIzmena.getNazivArtikla() : artikalDto.getNazivArtikla());
        artikalIzmena.setCena(artikalDto.getCena() == 0 ? artikalIzmena.getCena() : artikalDto.getCena());
        artikalIzmena.setTipArtikla(artikalDto.getTipArtikla() == null ? artikalIzmena.getTipArtikla() : artikalDto.getTipArtikla());
        artikalRepository.save(artikalIzmena);
        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(artikalIzmena);
    }

    @PostMapping("/api/menadzer/Upripremi")
    public ResponseEntity<Porudzbina> setUPripremi(@RequestParam UUID idPorudzbina, HttpSession session) {

        Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

        if(logovaniMenadzer==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaRepository.getById(idPorudzbina);
        porudzbina.setTrenutnoStanjePorudzbine(Status.U_PRIPREMI);
        porudzbinaRepository.save(porudzbina);
        return ResponseEntity.ok(porudzbina);
    }

@PostMapping("/api/menadzer/UCekaDostavljaca")
public ResponseEntity<Porudzbina> setUCekaDostavljaca(@RequestParam UUID idPorudzbina, HttpSession session) {

    Menadzer logovaniMenadzer = (Menadzer) session.getAttribute("menadzer");

    if(logovaniMenadzer==null)
    {
        return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
    }

    Porudzbina porudzbina = porudzbinaRepository.getById(idPorudzbina);
    porudzbina.setTrenutnoStanjePorudzbine(Status.CEKA_DOSTAVLJACA);
    porudzbinaRepository.save(porudzbina);
    return ResponseEntity.ok(porudzbina);
}

}

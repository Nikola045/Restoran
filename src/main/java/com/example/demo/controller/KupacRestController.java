package com.example.demo.controller;


import com.example.demo.dto.IzmeniKorisnikDto;
import com.example.demo.dto.KupacDto;
import com.example.demo.dto.LogInDto;
import com.example.demo.entity.Korpa;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class KupacRestController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping("/api/kupac/registracija")
    public String saveKupac(@RequestBody Kupac kupac) {
        this.kupacService.save(kupac);
        return "Upsesno ste se ulogovali kao novi kupac!";
    }

    @PostMapping("/api/kupac/prijava")
    public ResponseEntity<String> login(@RequestBody LogInDto loginDto, HttpSession session)
    {
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
        {
            return new ResponseEntity("Pogresni podaci!", HttpStatus.BAD_REQUEST);
        }

        Kupac logovaniKupac = kupacService.login(loginDto.getUsername(),loginDto.getPassword());

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Korisnik sa ovim podacima ne postoji!",HttpStatus.NOT_FOUND);
        }

        session.setAttribute("kupac",logovaniKupac);
        return ResponseEntity.ok("Kupac je uspesno prijavljen!");
    }

    @PostMapping("api/kupac/odajvi")
    public ResponseEntity Logout(HttpSession session){
        Kupac loggedKupac = (Kupac) session.getAttribute("kupac");

        if (loggedKupac == null)
            return new ResponseEntity("Kupac nije ni prijavljen", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Uspesno odjavljen kupac iz sistema", HttpStatus.OK);
    }

    @GetMapping("/api/kupac/PogledajPorudzbine")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        String username = logovaniKupac.getUsername();

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = kupacService.pregledajPorudzbine(username);

        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/kupac/PregledKorpe")
    public ResponseEntity<Korpa> pregledKorpe(HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Korpa korpa = korpaService.pregledajKorpu(logovaniKupac);
        return ResponseEntity.ok(korpa);

    }
//ne radi
    @PostMapping("/api/kupac/poruci")
    public ResponseEntity<Porudzbina> poruciIzRestorana(@RequestParam String restoranNaziv, HttpSession session)
    {
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");

        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Restoran restoran = restoranService.nadjiPoImenu(restoranNaziv);
        Porudzbina porudzbina = porudzbinaService.poruci(logovaniKupac,restoran);

        return ResponseEntity.ok(porudzbina);
    }

    @GetMapping("/api/kupac/profil")
    public ResponseEntity<KupacDto> getKupac(HttpSession session){
        Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");


        if(logovaniKupac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Kupac kupac = kupacService.findOne(logovaniKupac.getUsername());
        KupacDto dto = new KupacDto(kupac);
        return ResponseEntity.ok(dto);
    }
//ne radi
@PostMapping("/api/kupac/izmeni")
public ResponseEntity<Kupac> setKupac(HttpSession session, @RequestBody KupacDto kupacDto) {

    Kupac logovaniKupac = (Kupac) session.getAttribute("kupac");


    if(logovaniKupac==null)
    {
        return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
    }



    logovaniKupac.setUsername(kupacDto.getUsername() == null ? logovaniKupac.getUsername() : kupacDto.getUsername());
    logovaniKupac.setPassword(kupacDto.getPassword() == null ? logovaniKupac.getPassword() : kupacDto.getPassword());
    logovaniKupac.setIme(kupacDto.getIme() == null ? logovaniKupac.getIme() : kupacDto.getIme());
    logovaniKupac.setPrezime(kupacDto.getPrezime() == null ? logovaniKupac.getPrezime() : kupacDto.getPrezime());

    try {
        System.out.println("Uspesna izmena.");
    } catch (Exception e) {
        System.out.println("Neuspesna izmena.");
    }

    return ResponseEntity.ok(logovaniKupac);
}





}

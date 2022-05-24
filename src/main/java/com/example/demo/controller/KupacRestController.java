package com.example.demo.controller;


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
    public String saveEmployee(@RequestBody Kupac kupac) {
        this.kupacService.save(kupac);
        return "Successfully saved an kupac!";
    }

    @PostMapping("/api/kupac/login")
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
        return ResponseEntity.ok("Uspesno logovanje!");
    }

    @PostMapping("api/kupac/logout")
    public ResponseEntity Logout(HttpSession session){
        Kupac loggedKupac = (Kupac) session.getAttribute("kupac");

        if (loggedKupac == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Uspesno ste odjavljeni", HttpStatus.OK);
    }

    @GetMapping("/api/kupac/pregled-porudzbina")
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

    @GetMapping("/api/kupac/pregled-korpe")
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



}

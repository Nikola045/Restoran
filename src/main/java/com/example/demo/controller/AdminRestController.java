package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AdminRestController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    RestoranService restoranService;

    @Autowired
    KupacService kupacService;

    @GetMapping("/api/")
    public String welcome() {
        return "Hello from api!";
    }

    @PostMapping(value="/api/admin/prijava",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto, HttpSession session) {
        if (logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Admin loggedAdmin = adminService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedAdmin == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("admin", loggedAdmin);
        return ResponseEntity.ok("Admine uspesno ste izvrsili prijavu");
    }

    @PostMapping("/api/admin/DodajMenadzera")
    public ResponseEntity dodajM(@RequestBody Menadzer menadzer, HttpSession session) {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if (logovaniAdmin == null) {
            return new ResponseEntity("Samo admin moze da obavi ovu radnju!", HttpStatus.FORBIDDEN);
        }

        menadzerService.dodajMenadzera(menadzer);

        return new ResponseEntity("Menadzer je uspesno dodat!", HttpStatus.OK);
    }

    @PostMapping("/api/admin/DodajDostavljaca")
    public ResponseEntity dodajD(@RequestBody Dostavljac dostavljac, HttpSession session) {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if (logovaniAdmin == null) {
            return new ResponseEntity("Samo admin moze da obavi ovu radnju!", HttpStatus.FORBIDDEN);
        }

        dostavljacService.dodajDostavljaca(dostavljac);

        return new ResponseEntity("Dostavljac je usepsno dodat!", HttpStatus.OK);
    }

    @PostMapping("/api/admin/DodajRestoran")
    public ResponseEntity dodajR(@RequestBody Restoran restoran, HttpSession session) {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if (logovaniAdmin == null) {
            return new ResponseEntity("Samo admin moze da obavi ovu radnju!", HttpStatus.FORBIDDEN);
        }

        restoranService.dodajRestoran(restoran);

        return new ResponseEntity("Restoran je uspesno dodat!", HttpStatus.OK);
    }

    @PutMapping("/api/admin/zaduziRestoran/{nazivRestorana}")
    public ResponseEntity<MenadzerDto> addRestoran(@PathVariable String nazivRestorana, HttpSession session) {
        Menadzer loggedMenadzer = (Menadzer) session.getAttribute("menadzer");
        if (loggedMenadzer == null) {
            System.out.println("Nema sesije");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(loggedMenadzer);
        }
        Menadzer updatedMenadzer = menadzerService.postaviRestoran(loggedMenadzer.getUsername(), nazivRestorana);
        if (updatedMenadzer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new MenadzerDto(updatedMenadzer));

    }

    @GetMapping("/api/admin/pregledKorsinika")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session) {
        List<Menadzer> menadzerList = menadzerService.findAll();
        List<Dostavljac> dostavljacList = dostavljacService.findAll();
        List<Kupac> kupacList = kupacService.findAll();


        List<KorisnikDto> dtos = new ArrayList<>();
        for (Menadzer menadzer : menadzerList) {
            KorisnikDto dto = new KorisnikDto(menadzer);
            dtos.add(dto);
        }
        for (Dostavljac dostavljac : dostavljacList) {
            KorisnikDto dto = new KorisnikDto(dostavljac);
            dtos.add(dto);
        }
        for (Kupac kupac : kupacList) {
            KorisnikDto dto = new KorisnikDto(kupac);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/api/admin/odjavi")
    public ResponseEntity logout(HttpSession session) {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if (logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!", HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Admin je uspesno odjavljen iz sistema", HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/brisanjeRestorana")
    public ResponseEntity<String> obrisiRestoran(@RequestParam String nazivRestorana, HttpSession session) {
        Admin loggedAdmin = (Admin) session.getAttribute("admin");
        if (loggedAdmin == null) {
            return new ResponseEntity<>("Niste admin", HttpStatus.METHOD_NOT_ALLOWED);
        }
        adminService.obrisiRestoran(nazivRestorana);
        return ResponseEntity.ok("Restoran obrisan");
    }

    @PostMapping("/api/admin/pretragaPoImenu")
    public ResponseEntity<Set<KorisnikDto>> getKorisnikaPoImenu(@RequestParam String ime) {
        Set<KorisnikDto> menadzerList = menadzerService.findbyIme(ime);
        Set<KorisnikDto> dostavljacList = dostavljacService.findByIme(ime);
        Set<KorisnikDto> kupacList = kupacService.findByIme(ime);



        Set<KorisnikDto> dtos = new HashSet<>();
        dtos.addAll(menadzerList);
        dtos.addAll(dostavljacList);
        dtos.addAll(kupacList);

        return ResponseEntity.ok(dtos);


    }
    @PostMapping("/api/admin/pretragaPoPrezimenu")
    public ResponseEntity<Set<KorisnikDto>> getKorisnikaPoPrezimenu(@RequestParam String prezime) {
        Set<KorisnikDto> menadzerList = menadzerService.findbyPrezime(prezime);
        Set<KorisnikDto> dostavljacList = dostavljacService.findByPrezime(prezime);
        Set<KorisnikDto> kupacList = kupacService.findByPrezime(prezime);



        Set<KorisnikDto> dtos = new HashSet<>();
        dtos.addAll(menadzerList);
        dtos.addAll(dostavljacList);
        dtos.addAll(kupacList);

        return ResponseEntity.ok(dtos);


    }

    @PostMapping("/api/admin/pretragaPoUsername")
    public ResponseEntity<Set<KorisnikDto>> getKorisnikaPoUsername(@RequestParam String username) {
        Set<KorisnikDto> menadzerList = menadzerService.findbyUsername(username);
        Set<KorisnikDto> dostavljacList = dostavljacService.findByUsername(username);
        Set<KorisnikDto> kupacList = kupacService.findByUsername(username);



        Set<KorisnikDto> dtos = new HashSet<>();
        dtos.addAll(menadzerList);
        dtos.addAll(dostavljacList);
        dtos.addAll(kupacList);

        return ResponseEntity.ok(dtos);


    }
}
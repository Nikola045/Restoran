package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.KupacService;
import com.example.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    KupacService kupacService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/admin/prijava")
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto, HttpSession session){
        if(logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Admin loggedAdmin = adminService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedAdmin == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("admin", loggedAdmin);
        return ResponseEntity.ok("Admine uspesno ste izvrsili prijavu");
    }

    @PostMapping("/api/admin/DodajMenadzera")
    public ResponseEntity dodajM(@RequestBody Menadzer menadzer, HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Samo admin moze da obavi ovu radnju!",HttpStatus.FORBIDDEN);
        }

        menadzerService.dodajMenadzera(menadzer);

        return new ResponseEntity("Menadzer je uspesno dodat!",HttpStatus.OK);
    }

    @PostMapping("/api/admin/DodajDostavljaca")
    public ResponseEntity dodajD(@RequestBody Dostavljac dostavljac, HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Samo admin moze da obavi ovu radnju!",HttpStatus.FORBIDDEN);
        }

        dostavljacService.dodajDostavljaca(dostavljac);

        return new ResponseEntity("Dostavljac je usepsno dodat!",HttpStatus.OK);
    }

    @GetMapping("/api/admin/pregledKorsinika")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session)
    {
        List<Menadzer> menadzerList = menadzerService.findAll();
        List<Dostavljac> dostavljacList = dostavljacService.findAll();
        List<Kupac> kupacList = kupacService.findAll();


        List<KorisnikDto> dtos = new ArrayList<>();
        for(Menadzer menadzer : menadzerList){
            KorisnikDto dto = new KorisnikDto(menadzer);
            dtos.add(dto);
        }
        for(Dostavljac dostavljac : dostavljacList){
            KorisnikDto dto = new KorisnikDto(dostavljac);
            dtos.add(dto);
        }
        for(Kupac kupac : kupacList){
            KorisnikDto dto = new KorisnikDto(kupac);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/api/admin/odjavi")
    public ResponseEntity logout(HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Admin je uspesno odjavljen iz sistema",HttpStatus.OK);
    }



}

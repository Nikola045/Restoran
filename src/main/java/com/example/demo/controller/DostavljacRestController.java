package com.example.demo.controller;

import com.example.demo.dto.LogInDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Status;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class DostavljacRestController {

    @Autowired
    private DostavljacService dostavljacService;
    @Autowired
    private PorudzbinaService porudzbinaService;

    @PostMapping("api/dostavljac/login")
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto, HttpSession session){
        if(logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Dostavljac loggedDostavljac = dostavljacService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedDostavljac == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("dostavljac", loggedDostavljac);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @GetMapping("/api/dostavljac/pregled-porudzbina-zaduzen")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        String username = logovaniDostavljac.getUsername();

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = dostavljacService.pregledajPorudzbineZaduzen(username);

        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/dostavljac/pregled-porudzbina-slobodne")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbinaSlobodne(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        String username = logovaniDostavljac.getUsername();

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = porudzbinaService.pregledajPorudzbineSlobodne(Status.CEKA_DOSTAVLJACA);

        return ResponseEntity.ok(porudzbine);
    }

    @PostMapping("/api/dostavljac/logout")
    public ResponseEntity logout(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null)
        {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Dostavljac je odjavljen!",HttpStatus.OK);
    }
}

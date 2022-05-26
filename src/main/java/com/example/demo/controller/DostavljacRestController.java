package com.example.demo.controller;

import com.example.demo.dto.DostavljacDto;
import com.example.demo.dto.KupacDto;
import com.example.demo.dto.LogInDto;
import com.example.demo.entity.*;
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

    @PostMapping("api/dostavljac/prijava")
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto, HttpSession session){
        if(logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Podaci nisu dobro uneti", HttpStatus.BAD_REQUEST);

        Dostavljac loggedDostavljac = dostavljacService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedDostavljac == null)
            return new ResponseEntity<>("Korisnik ne postoji u sistemu", HttpStatus.NOT_FOUND);

        session.setAttribute("dostavljac", loggedDostavljac);
        return ResponseEntity.ok("Dostavljac je uspesno prijavljen");
    }

    @PostMapping("/api/dostavljac/odjavi")
    public ResponseEntity logout(HttpSession session)
    {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac == null)
        {
            return new ResponseEntity("Dostavljac nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Dostavljac je uspesno odjavljen iz sistema!",HttpStatus.OK);
    }

    @GetMapping("/api/dostavljac/profil")
    public ResponseEntity<DostavljacDto> getDostavljac(HttpSession session){
        Dostavljac logovaniDostavljac= (Dostavljac) session.getAttribute("dostavljac");


        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Dostavljac dostavljac = dostavljacService.findOne(logovaniDostavljac.getUsername());
        DostavljacDto dto = new DostavljacDto(dostavljac);
        return ResponseEntity.ok(dto);
    }

}

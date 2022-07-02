package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.DostavljacDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class DostavljacRestController {

    @Autowired
    private DostavljacService dostavljacService;
    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @PostMapping(value="/api/dostavljac/prijava",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogInDto> login(@RequestBody LogInDto logInDto, HttpSession session) {
        if (logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Podaci nisu dobro uneti", HttpStatus.BAD_REQUEST);

        Dostavljac loggedDostavljac = dostavljacService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedDostavljac == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        session.setAttribute("dostavljac", loggedDostavljac);
        return new ResponseEntity<>(logInDto,HttpStatus.OK);
    }

    @PostMapping("/api/dostavljac/odjavi")
    public ResponseEntity logout(HttpSession session) {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if (logovaniDostavljac == null) {
            return new ResponseEntity("Dostavljac nije logovan!", HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Dostavljac je uspesno odjavljen iz sistema!", HttpStatus.OK);
    }

    @GetMapping("/api/dostavljac/profil")
    public ResponseEntity<DostavljacDto> getDostavljac(HttpSession session) {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");


        if (logovaniDostavljac == null) {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        Dostavljac dostavljac = dostavljacService.findOne(logovaniDostavljac.getUsername());
        DostavljacDto dto = new DostavljacDto(dostavljac);
        return ResponseEntity.ok(dto);
    }


    @PostMapping(value="/api/dostavljac/izmeni",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Dostavljac> setDostavljac(HttpSession session, @RequestBody DostavljacDto dostavljacDto) {

        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if (logovaniDostavljac == null) {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }

        logovaniDostavljac.setUsername(dostavljacDto.getUsername() == null ? logovaniDostavljac.getUsername() : dostavljacDto.getUsername());
        logovaniDostavljac.setPassword(dostavljacDto.getPassword() == null ? logovaniDostavljac.getPassword() : dostavljacDto.getPassword());
        logovaniDostavljac.setIme(dostavljacDto.getIme() == null ? logovaniDostavljac.getIme() : dostavljacDto.getIme());
        logovaniDostavljac.setPrezime(dostavljacDto.getPrezime() == null ? logovaniDostavljac.getPrezime() : dostavljacDto.getPrezime());

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(logovaniDostavljac);
    }


    @GetMapping("/api/dostavljac/porudzbinaCeka")
    public ResponseEntity<List<Porudzbina>> getPorudzbineCeka(HttpSession session) {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");


        if (logovaniDostavljac == null) {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<Porudzbina> pronadjenePorudzbine = new ArrayList<>();
        for (Porudzbina porudzbinaCeka : porudzbine) {
            if (porudzbinaCeka.getTrenutnoStanjePorudzbine() == Status.CEKA_DOSTAVLJACA)
                {
                    pronadjenePorudzbine.add(porudzbinaCeka);
                }

        }
        return ResponseEntity.ok(pronadjenePorudzbine);
    }

    @GetMapping("/api/dostavljac/porudzbinaZaduzen")
    public ResponseEntity<List<Porudzbina>> getPorudzbineZaduzen(HttpSession session) {
        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");


        if (logovaniDostavljac == null) {
            return new ResponseEntity("Niste ulogovani!", HttpStatus.FORBIDDEN);
        }
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<Porudzbina> pronadjenePorudzbine = new ArrayList<>();
        for (Porudzbina porudzbinaZaduzen : porudzbine) {
            if (porudzbinaZaduzen.getDostavljac()==logovaniDostavljac)
            {
                pronadjenePorudzbine.add(porudzbinaZaduzen);
            }

        }
        return ResponseEntity.ok(pronadjenePorudzbine);
    }

    @PostMapping("/api/dostavljac/UTransportu")
    public ResponseEntity<Porudzbina> setUTransportu(@RequestParam UUID idPorudzbina, HttpSession session) {

        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaRepository.getById(idPorudzbina);
        porudzbina.setTrenutnoStanjePorudzbine(Status.U_TRANSPORTU);
        porudzbina.setDostavljac(logovaniDostavljac);
        porudzbinaRepository.save(porudzbina);
        return ResponseEntity.ok(porudzbina);
    }
    @PostMapping("/api/dostavljac/UDostavljena")
    public ResponseEntity<Porudzbina> setDostavljena(@RequestParam UUID idPorudzbina, HttpSession session) {

        Dostavljac logovaniDostavljac = (Dostavljac) session.getAttribute("dostavljac");

        if(logovaniDostavljac==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Porudzbina porudzbina = porudzbinaRepository.getById(idPorudzbina);
        porudzbina.setTrenutnoStanjePorudzbine(Status.DOSTAVLJENA);
        porudzbina.setDostavljac(logovaniDostavljac);
        porudzbinaRepository.save(porudzbina);
        return ResponseEntity.ok(porudzbina);
    }
}



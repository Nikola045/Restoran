package com.example.demo.controller;

import com.example.demo.dto.LogInDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Menadzer;
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

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/admin/login")
    public ResponseEntity<String> login(@RequestBody LogInDto logInDto, HttpSession session){
        if(logInDto.getUsername().isEmpty() || logInDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Admin loggedAdmin = adminService.login(logInDto.getUsername(), logInDto.getPassword());
        if (loggedAdmin == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("admin", loggedAdmin);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("/api/admin/dodaj-menadzer")
    public ResponseEntity dodajM(@RequestBody Menadzer menadzer, HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        menadzerService.dodajMenadzera(menadzer);

        return new ResponseEntity("Menadzer dodat!",HttpStatus.OK);
    }

    @PostMapping("/api/admin/dodaj-dostavljac")
    public ResponseEntity dodajD(@RequestBody Dostavljac dostavljac, HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null) {
            return new ResponseEntity("Dostavljac dodat!",HttpStatus.FORBIDDEN);
        }

        dostavljacService.dodajDostavljaca(dostavljac);

        return new ResponseEntity("Admin nije logovan!",HttpStatus.OK);
    }

    @PostMapping("/api/admin/logout")
    public ResponseEntity logout(HttpSession session)
    {
        Admin logovaniAdmin = (Admin) session.getAttribute("admin");

        if(logovaniAdmin == null)
        {
            return new ResponseEntity("Admin nije logovan!",HttpStatus.FORBIDDEN);
        }

        session.invalidate();
        return new ResponseEntity("Admin odjavljen!",HttpStatus.OK);
    }



}

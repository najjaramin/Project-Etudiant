package com.example.etudiants.controller;

import com.example.etudiants.dto.EtudiantDTO;
import com.example.etudiants.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController 
@RequestMapping("/api/etudiants")
@CrossOrigin("*")
public class EtudiantController {

    private final EtudiantService service;

    // Constructeur pour l'injection de dépendance (Remplace Lombok)
    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @GetMapping
    public List<EtudiantDTO> getAll(@RequestParam(required = false) Integer annee) {
        if (annee != null) {
            return service.findByAnnee(annee);
        }
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtudiantDTO create(@RequestBody EtudiantDTO dto) {
        return service.save(dto);
    }
}
package com.example.etudiants.controller;

import com.example.etudiants.dto.EtudiantDTO;
import com.example.etudiants.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/etudiants")
@RequiredArgsConstructor @CrossOrigin("*")
public class EtudiantController {
    private final EtudiantService service;

    @GetMapping
    @Operation(summary = "Liste tous les étudiants")
    public List<EtudiantDTO> getAll(@RequestParam(required = false) Integer annee) {
        if (annee != null) return service.findByAnnee(annee);
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtudiantDTO create(@RequestBody EtudiantDTO dto) {
        return service.save(dto);
    }
}
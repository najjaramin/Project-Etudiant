package com.example.etudiants.service;

import com.example.etudiants.dto.EtudiantDTO;
import com.example.etudiants.entity.Etudiant;
import com.example.etudiants.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class EtudiantService {
    private final EtudiantRepository repository;

    @Cacheable(value = "etudiants")
    public List<EtudiantDTO> findAll() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<EtudiantDTO> findByAnnee(int annee) {
        return repository.findByAnneePremiereInscription(annee).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @CacheEvict(value = "etudiants", allEntries = true)
    public EtudiantDTO save(EtudiantDTO dto) {
        Etudiant e = Etudiant.builder()
                .cin(dto.getCin()).nom(dto.getNom())
                .dateNaissance(dto.getDateNaissance()).email(dto.getEmail())
                .build();
        return mapToDTO(repository.save(e));
    }

    private EtudiantDTO mapToDTO(Etudiant e) {
        return EtudiantDTO.builder()
                .id(e.getId()).cin(e.getCin()).nom(e.getNom())
                .dateNaissance(e.getDateNaissance()).email(e.getEmail())
                .age(e.age()).build();
    }
}
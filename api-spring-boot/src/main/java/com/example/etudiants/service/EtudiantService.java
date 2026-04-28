package com.example.etudiants.service;

import com.example.etudiants.dto.EtudiantDTO;
import com.example.etudiants.entity.Etudiant;
import com.example.etudiants.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantService {
    private final EtudiantRepository repository;

    public EtudiantService(EtudiantRepository repository) { 
        this.repository = repository; 
    }

    public List<EtudiantDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // AJOUT DE LA MÉTHODE MANQUANTE POUR LE CONTROLLER
    public List<EtudiantDTO> findByAnnee(int annee) {
        return repository.findByAnneePremiereInscription(annee).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EtudiantDTO save(EtudiantDTO dto) {
        Etudiant e = new Etudiant(dto.getCin(), dto.getNom(), dto.getDateNaissance(), dto.getEmail());
        Etudiant saved = repository.save(e);
        return mapToDTO(saved);
    }

    private EtudiantDTO mapToDTO(Etudiant e) {
        return new EtudiantDTO(e.getId(), e.getCin(), e.getNom(), e.getDateNaissance(), e.getEmail(), e.age());
    }
}
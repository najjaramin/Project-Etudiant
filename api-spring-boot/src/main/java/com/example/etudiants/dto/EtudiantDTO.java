package com.example.etudiants.dto;
import java.time.LocalDate;

public class EtudiantDTO {
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int age;

    public EtudiantDTO() {}
    public EtudiantDTO(Long id, String cin, String nom, LocalDate dateNaissance, String email, int age) {
        this.id = id; this.cin = cin; this.nom = nom; this.dateNaissance = dateNaissance; this.email = email; this.age = age;
    }
    // Getters
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getEmail() { return email; }
}
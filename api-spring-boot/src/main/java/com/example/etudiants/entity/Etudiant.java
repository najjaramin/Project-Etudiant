package com.example.etudiants.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int anneePremiereInscription;

    public Etudiant() {}
    public Etudiant(String cin, String nom, LocalDate dateNaissance, String email) {
        this.cin = cin; this.nom = nom; this.dateNaissance = dateNaissance; this.email = email;
    }

    public int age() { return Period.between(this.dateNaissance, LocalDate.now()).getYears(); }
    
    // Getters et Setters
    public Long getId() { return id; }
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getEmail() { return email; }
}
package com.example.etudiants.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int anneePremiereInscription;

    @ManyToOne
    private Departement departement;

    public int age() {
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }
}
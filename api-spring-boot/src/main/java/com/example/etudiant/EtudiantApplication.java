package com.example.etudiant;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EtudiantApplication {
    public static void main(String[] args) { SpringApplication.run(EtudiantApplication.class, args); }

    @Bean
    CommandLineRunner start(EtudiantRepository repo) {
        return args -> {
            repo.save(new Etudiant("123456", "Amin Najjar", LocalDate.of(2000, 5, 10)));
            repo.save(new Etudiant("654321", "Sami Ben Ali", LocalDate.of(1999, 3, 15)));
            repo.save(new Etudiant("789123", "Amira Toumi", LocalDate.of(2001, 1, 20)));
            repo.save(new Etudiant("456789", "Yassine Dridi", LocalDate.of(2000, 11, 30)));
            repo.save(new Etudiant("321654", "Leila Kamel", LocalDate.of(1998, 7, 12)));
        };
    }
}

@Entity
class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;

    public Etudiant() {}
    public Etudiant(String cin, String nom, LocalDate dateNaissance) {
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
    // Getters
    public Long getId() { return id; }
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
}

interface EtudiantRepository extends JpaRepository<Etudiant, Long> {}

@RestController @RequestMapping("/api/etudiants") @CrossOrigin("*")
class EtudiantController {
    private final EtudiantRepository repo;
    public EtudiantController(EtudiantRepository repo) { this.repo = repo; }
    @GetMapping
    public List<Etudiant> get() { return repo.findAll(); }
}

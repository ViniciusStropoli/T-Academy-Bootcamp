package com.phainon.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Classificacao")
public class Classificacao {
    @Id
    private int id;

    @Column(name = "Nivel_Risco")
    private String nivelRisco;

    // Getters e Setters
}

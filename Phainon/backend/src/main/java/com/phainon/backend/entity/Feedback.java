package com.viagemnotempo.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    private int id;

    private int nota;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "PacoteViagem_idPacoteViagem")
    private PacoteViagem pacoteViagem;

    // Getters e Setters
}

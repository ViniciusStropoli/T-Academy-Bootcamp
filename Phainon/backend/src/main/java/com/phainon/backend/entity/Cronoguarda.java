package com.viagemnotempo.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cronoguarda")
public class Cronoguarda {
    @Id
    private int idCronoguarda;

    private String nome;

    @Enumerated(EnumType.STRING)
    private NivelRisco nivelRisco;

    private String especialidade;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "PacoteViagem_idPacoteViagem")
    private PacoteViagem pacoteViagem;

    // Getters e Setters

    public enum NivelRisco {
        BAIXO, MEDIO, ALTO
    }
}

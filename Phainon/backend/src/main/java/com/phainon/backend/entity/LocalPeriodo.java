package com.viagemnotempo.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Local_periodo")
public class LocalPeriodo {
    @Id
    private int idPeriodo;

    private String local;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "Viagem_idViagem")
    private Viagem viagem;

    // Getters e Setters
}

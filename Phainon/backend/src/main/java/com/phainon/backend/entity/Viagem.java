package com.viagemnotempo.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Viagem")
public class Viagem {
    @Id
    private int idViagem;

    private String local;

    @Column(name = "Data_ida")
    private Date dataIda;

    @Column(name = "Data_volta")
    private Date dataVolta;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "PacoteViagem_idPacoteViagem")
    private PacoteViagem pacoteViagem;

    // Getters e Setters
}

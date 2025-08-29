package com.phainon.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PacoteViagem")
public class PacoteViagem {
    @Id
    private int idPacoteViagem;

    @Column(name = "Nome_Pacote")
    private String nomePacote;

    private double preco;
    private String descricao;

    @Column(name = "data_compra")
    private Date dataCompra;

    @ManyToOne
    @JoinColumn(name = "Classificacao_ID")
    private Classificacao classificacao;

    // Getters e Setters
}

package com.viagemnotempo.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Instituicao")
public class Instituicao {
    @Id
    private int idInstituicao;

    private String nome;
    private String cidade;
    private String telefone;
    private String email;

    // Getters e Setters
}

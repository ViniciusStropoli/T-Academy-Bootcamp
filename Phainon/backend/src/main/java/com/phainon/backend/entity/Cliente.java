package com.phainon.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    private int idCliente;

    @Column(name = "Cliente_Epoca_Existencia")
    private Date clienteEpocaExistencia;

    private String nome;
    private String identidade;
    private String email;

    @Column(name = "Data_Nascimento")
    private Date dataNascimento;

    private String senha;
    private boolean visto;

    // Getters e Setters
}

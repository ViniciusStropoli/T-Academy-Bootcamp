package com.sistema.Projeto04.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "dataCriacao")
    private LocalDate dataCriacao;

    @Column(name = "status")
    private String status;

    @Column(name = "prazo")
    private LocalDate prazo;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade")
    private Prioridade prioridade;
}

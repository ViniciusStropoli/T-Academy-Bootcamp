package com.aula.projeto03.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String nome;

    @Column(length = 20)
    private String data;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}

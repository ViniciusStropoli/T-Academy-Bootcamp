package com.sistema.Projeto04.Dtos;

import com.sistema.Projeto04.models.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TarefaDto(
        Long id,

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        String descricao,

        LocalDate dataCriacao,

        LocalDate prazo,

        @NotBlank(message = "O status é obrigatório")
        String status,

        @NotNull(message = "A prioridade é obrigatória")
        Prioridade prioridade,

        @NotNull(message = "A categoria é obrigatória")
        Long categoriaId

) {}

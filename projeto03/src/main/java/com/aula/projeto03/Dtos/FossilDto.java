package com.aula.projeto03.Dtos;

import java.time.LocalDate;

public record FossilDto(
        String nome,
        String descricao,
        LocalDate descobertoEm,
        Long periodoId
) {}

package com.viagemnotempo.backend.repository;

import com.viagemnotempo.backend.entity.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Integer> {
}

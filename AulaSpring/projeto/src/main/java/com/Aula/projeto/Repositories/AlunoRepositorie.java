package com.Aula.projeto.Repositories;

import com.Aula.projeto.Models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepositorie extends JpaRepository<Aluno, Long> {
}

package com.aula.projeto03.repositories;

import com.aula.projeto03.models.Fossil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FossilRepository extends JpaRepository<Fossil, Long> {
}

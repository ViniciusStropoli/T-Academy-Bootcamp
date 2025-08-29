package com.viagemnotempo.backend.repository;

import com.viagemnotempo.backend.entity.LocalPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalPeriodoRepository extends JpaRepository<LocalPeriodo, Integer> {
}

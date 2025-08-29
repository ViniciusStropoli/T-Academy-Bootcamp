package com.viagemnotempo.backend.repository;

import com.viagemnotempo.backend.entity.Cronoguarda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronoguardaRepository extends JpaRepository<Cronoguarda, Integer> {
}

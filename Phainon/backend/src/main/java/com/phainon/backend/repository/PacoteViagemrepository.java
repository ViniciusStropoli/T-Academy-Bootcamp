package com.viagemnotempo.backend.repository;

import com.viagemnotempo.backend.entity.PacoteViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteViagemRepository extends JpaRepository<PacoteViagem, Integer> {
}

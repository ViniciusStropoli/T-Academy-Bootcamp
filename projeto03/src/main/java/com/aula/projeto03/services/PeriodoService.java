package com.aula.projeto03.services;

import com.aula.projeto03.models.Periodo;
import com.aula.projeto03.repositories.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    @GetMapping
    public List<Periodo> findAll() {
        return periodoRepository.findAll();
    }

    @PostMapping
    public Periodo create(Periodo periodo) {
        return periodoRepository.save(periodo);
    }

    @GetMapping("/{id}")
    public Periodo findById(Long id) {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Período não encontrado com ID: " + id));
    }

    @PutMapping
    public Periodo update(Long id, Periodo periodoAtualizado) {
        Periodo periodoExistente = findById(id);
        periodoExistente.setNome(periodoAtualizado.getNome());
        periodoExistente.setData(periodoAtualizado.getData());
        return periodoRepository.save(periodoExistente);
    }

    @DeleteMapping
    public void delete(Long id) {
        Periodo periodo = findById(id);
        periodoRepository.delete(periodo);
    }
}

package com.aula.projeto03.services;

import com.aula.projeto03.models.Fossil;
import com.aula.projeto03.repositories.FossilRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FossilService {

    private final FossilRepository fossilRepository;

    public FossilService(FossilRepository fossilRepository) {
        this.fossilRepository = fossilRepository;
    }

    public List<Fossil> findAll() {
        return fossilRepository.findAll();
    }

    public Fossil findById(Long id) {
        Optional<Fossil> fossil = fossilRepository.findById(id);
        if (fossil.isPresent()) {
            return fossil.get();
        } else {
            throw new RuntimeException("Fóssil com ID " + id + " não encontrado.");
        }
    }

    public Fossil create(Fossil fossil) {
        return fossilRepository.save(fossil);
    }

    public Fossil update(Long id, Fossil fossilAtualizado) {
        Fossil fossilExistente = findById(id);

        fossilExistente.setNome(fossilAtualizado.getNome());
        fossilExistente.setDescricao(fossilAtualizado.getDescricao());
        fossilExistente.setPeriodo(fossilAtualizado.getPeriodo());
        fossilExistente.setDescobertoEm(fossilAtualizado.getDescobertoEm());
        fossilExistente.setCategoria(fossilAtualizado.getCategoria());

        return fossilRepository.save(fossilExistente);
    }

    public void delete(Long id) {
        Fossil fossil = findById(id);
        fossilRepository.delete(fossil);
    }
}

package com.sistema.Projeto04.services;

import com.sistema.Projeto04.models.Tarefa;
import com.sistema.Projeto04.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa findById(Long id) {
            return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public List<Tarefa> findByStatus(String status) {
        return tarefaRepository.findByStatus(status);
    }

    public Tarefa create(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = findById(id);
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        return tarefaRepository.save(tarefa);
    }

    public void delete(Long id) {
        Tarefa tarefa = findById(id);
        tarefaRepository.delete(tarefa);
    }

}

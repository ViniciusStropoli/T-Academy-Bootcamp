package com.sistema.Projeto04.controllers;

import com.sistema.Projeto04.Dtos.TarefaDto;
import com.sistema.Projeto04.models.Categoria;
import com.sistema.Projeto04.models.Prioridade;
import com.sistema.Projeto04.models.Tarefa;
import com.sistema.Projeto04.services.CategoriaService;
import com.sistema.Projeto04.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;
    private final CategoriaService categoriaService;

    public TarefaController(TarefaService tarefaService, CategoriaService categoriaService) {
        this.tarefaService = tarefaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll() {
        return ResponseEntity.ok(tarefaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Tarefa tarefa = tarefaService.findById(id);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return buildErrorResponse("Não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Tarefa>> findByStatus(@RequestParam String status) {
        List<Tarefa> tarefas = tarefaService.findByStatus(status);
        return ResponseEntity.ok(tarefas);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TarefaDto tarefaDTO) {
        try {
            if (tarefaDTO.prazo() != null && tarefaDTO.dataCriacao() != null
                    && tarefaDTO.prazo().isBefore(tarefaDTO.dataCriacao())) {
                return buildErrorResponse("Erro de validação", "Prazo não pode ser anterior à data de criação", HttpStatus.BAD_REQUEST);
            }

            Categoria categoria = categoriaService.findById(tarefaDTO.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(tarefaDTO.titulo());
            tarefa.setDescricao(tarefaDTO.descricao());
            tarefa.setDataCriacao(tarefaDTO.dataCriacao() != null ? tarefaDTO.dataCriacao() : LocalDate.now());
            tarefa.setPrazo(tarefaDTO.prazo());
            tarefa.setStatus(tarefaDTO.status());
            tarefa.setPrioridade(tarefaDTO.prioridade());
            tarefa.setCategoria(categoria);

            Tarefa tarefaSalva = tarefaService.create(tarefa);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao criar tarefa", e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TarefaDto tarefaDTO) {
        try {
            if (tarefaDTO.prazo() != null && tarefaDTO.dataCriacao() != null
                    && tarefaDTO.prazo().isBefore(tarefaDTO.dataCriacao())) {
                return buildErrorResponse("Erro de validação", "Prazo não pode ser anterior à data de criação", HttpStatus.BAD_REQUEST);
            }

            Categoria categoria = categoriaService.findById(tarefaDTO.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            Tarefa tarefaAtualizada = new Tarefa();
            tarefaAtualizada.setTitulo(tarefaDTO.titulo());
            tarefaAtualizada.setDescricao(tarefaDTO.descricao());
            tarefaAtualizada.setDataCriacao(tarefaDTO.dataCriacao() != null ? tarefaDTO.dataCriacao() : LocalDate.now());
            tarefaAtualizada.setPrazo(tarefaDTO.prazo());
            tarefaAtualizada.setStatus(tarefaDTO.status());
            tarefaAtualizada.setPrioridade(tarefaDTO.prioridade());
            tarefaAtualizada.setCategoria(categoria);

            Tarefa tarefa = tarefaService.update(id, tarefaAtualizada);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao atualizar tarefa", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            tarefaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao deletar tarefa", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);

        return ResponseEntity.status(status).body(body);
    }
}

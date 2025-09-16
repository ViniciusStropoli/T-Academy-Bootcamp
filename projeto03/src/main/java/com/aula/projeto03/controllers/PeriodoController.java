package com.aula.projeto03.controllers;

import com.aula.projeto03.models.Periodo;
import com.aula.projeto03.repositories.PeriodoRepository;
import com.aula.projeto03.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    public PeriodoController(PeriodoService fossilservice) {
        this.periodoService = periodoService;
    }

    @PostMapping
    public Periodo add(@RequestBody Periodo periodo) {
        return periodoService.create(periodo);
    }

    @GetMapping
    public List<Periodo> findAll() {
        return periodoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(periodoService.findById(id));
        } catch (RuntimeException e) {
            return buildErrorResponse("Período não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
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

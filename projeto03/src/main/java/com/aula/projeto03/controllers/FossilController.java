package com.aula.projeto03.controllers;

import com.aula.projeto03.models.Fossil;
import com.aula.projeto03.services.FossilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fossil")
public class FossilController {

    @Autowired
    private FossilService fossilService;

    public FossilController(FossilService fossilService) {
        this.fossilService = fossilService;
    }

    @GetMapping
    public ResponseEntity<List<Fossil>> getAll() {
        return ResponseEntity.ok(fossilService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(fossilService.findById(id));
        } catch (RuntimeException e) {
            return buildErrorResponse("Fóssil não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Fossil> add(@RequestBody Fossil fossil) {
        Fossil novoFossil = fossilService.create(fossil);
        return ResponseEntity.ok(novoFossil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fossil fossil) {
        try {
            return ResponseEntity.ok(fossilService.update(id, fossil));
        } catch (RuntimeException e) {
            return buildErrorResponse("Fóssil não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            fossilService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildErrorResponse("Fóssil não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
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

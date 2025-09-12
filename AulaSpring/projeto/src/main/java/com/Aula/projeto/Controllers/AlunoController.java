package com.Aula.projeto.Controllers;

import com.Aula.projeto.Models.Aluno;
import com.Aula.projeto.Repositories.AlunoRepositorie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")

public class AlunoController {
    private AlunoRepositorie alunoRepositorie;

    public AlunoController (AlunoRepositorie alunoRepositorie) {
        this.alunoRepositorie = alunoRepositorie;
    }

    @GetMapping
    public List<Aluno> getAll() {
        return alunoRepositorie.findAll();
    }

    @PostMapping
    public String add(@RequestBody Aluno aluno) {
        alunoRepositorie.save(aluno);
        return "Aluno criado com sucesso!";
    }

}

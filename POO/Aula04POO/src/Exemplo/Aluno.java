package Exemplo;

import java.util.ArrayList;

public class Aluno extends Pessoa {
    private int matricula;
    private ArrayList<Double> notas;

    public Aluno(int id, String nome, int cpf) {
        super(id, nome, cpf);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }
}

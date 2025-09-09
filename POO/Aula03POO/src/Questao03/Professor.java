package Questao03;

import java.util.ArrayList;

public class Professor extends Pessoa {
    private ArrayList<Missao> missoesCriadas = new ArrayList<>();

    public Professor(String nome, String email) {
        super(nome, email);
    }

    public Missao criarMissao(String titulo, Recompensa recompensa) {
        Missao m = new Missao(titulo, recompensa);
        missoesCriadas.add(m);
        System.out.println("Missão criada: " + titulo);
        return m;
    }

    public ArrayList<Missao> getMissoesCriadas() {
        return missoesCriadas;
    }
}

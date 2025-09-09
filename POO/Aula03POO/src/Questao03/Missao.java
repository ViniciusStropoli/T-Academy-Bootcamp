package Questao03;

import java.util.ArrayList;

public class Missao {
    private String titulo;
    private ArrayList<Desafio> desafios = new ArrayList<>();
    private Recompensa recompensa;

    public Missao(String titulo, Recompensa recompensa) {
        this.titulo = titulo;
        this.recompensa = recompensa;
    }

    public void adicionarDesafio(Desafio d) {
        desafios.add(d);
    }

    public String getTitulo() {
        return titulo;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public ArrayList<Desafio> getDesafios() {
        return desafios;
    }
}

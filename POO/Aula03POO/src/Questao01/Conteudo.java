package Questao01;

import java.util.ArrayList;

public class Conteudo {
    private String nome;
    private String duracao;
    private Categoria categoria;
    private ArrayList<String> comentarios = new ArrayList<>();
    private int curtidas = 0;
    private double estrelas = 0.0;
    private int numAvaliacoes = 0;

    public enum Categoria {
        FILME, SERIE, MUSICA, DOCUMENTARIO
    }

    public Conteudo(String nome, String duracao, Categoria categoria) {
        this.nome = nome;
        this.duracao = duracao;
        this.categoria = categoria;
    }

    public void curtir() {
        curtidas++;
    }

    public void comentar(String comentario) {
        comentarios.add(comentario);
    }

    public void avaliarEstrelas(double estrelas) {
        this.estrelas = ((this.estrelas * numAvaliacoes) + estrelas) / (++numAvaliacoes);
    }

    public String getNome() { return nome; }
    public String getDuracao() { return duracao; }
    public Categoria getCategoria() { return categoria; }
    public int getCurtidas() { return curtidas; }
    public double getEstrelas() { return estrelas; }
    public ArrayList<String> getComentarios() { return comentarios; }
}

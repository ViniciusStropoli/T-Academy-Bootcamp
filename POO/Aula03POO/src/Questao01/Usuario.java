package Questao01;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Plano plano;
    private ArrayList<Conteudo> historico = new ArrayList<>();
    private ArrayList<Conteudo> playlist = new ArrayList<>();
    private double tempoTotalAssistido = 0.0;

    public enum Plano {
        GRATUITO, PREMIUM
    }

    public Usuario(String nome, String email, String senha, Plano plano) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;
    }

    public void assistirConteudo(Conteudo conteudo, double tempoAssistido) {
        historico.add(conteudo);
        tempoTotalAssistido += tempoAssistido;
        System.out.println(nome + " assistiu " + conteudo.getNome() + " por " + tempoAssistido + " minutos.");
    }

    public void adicionarAPlaylist(Conteudo conteudo) {
        playlist.add(conteudo);
    }

    public void avaliarConteudo(Conteudo conteudo, double estrelas, String comentario) {
        conteudo.avaliarEstrelas(estrelas);
        conteudo.comentar(comentario);
        conteudo.curtir();
    }

    public String getNome() { return nome; }
    public Plano getPlano() { return plano; }
    public ArrayList<Conteudo> getHistorico() { return historico; }
    public ArrayList<Conteudo> getPlaylist() { return playlist; }
    public double getTempoTotalAssistido() { return tempoTotalAssistido; }
}

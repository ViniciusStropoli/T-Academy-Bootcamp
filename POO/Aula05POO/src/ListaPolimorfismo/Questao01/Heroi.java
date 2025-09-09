package ListaPolimorfismo.Questao01;

import java.util.List;
import java.util.Random;

public abstract class Heroi {
    protected String nome;
    protected String bordao;
    protected List<String> poderes;

    public Heroi(String nome, String bordao, List<String> poderes) {
        this.nome = nome;
        this.bordao = bordao;
        this.poderes = poderes;
    }

    public void apresentar() {
        System.out.println("🦸‍♂️ Herói: " + nome);
        System.out.println("💬 Bordão: \"" + bordao + "\"");
    }

    public void usarPoder() {
        Random rand = new Random();
        String poder = poderes.get(rand.nextInt(poderes.size()));
        System.out.println("⚡ " + nome + " usou o poder: " + poder + "!");
    }

    public abstract void agirEmMissao();
}

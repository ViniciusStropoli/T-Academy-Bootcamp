package Questao03;

public class Personagem {
    private String nome;
    private int nivel;
    private int xp;

    public Personagem(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.xp = 0;
    }

    public void ganharXp(int valor) {
        xp += valor;
        while (xp >= nivel * 100) {
            xp -= nivel * 100;
            nivel++;
            System.out.println(nome + " subiu para o nível " + nivel + "!");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }
}

package Questao03;

public class Aluno extends Pessoa {
    private Personagem personagem;
    private int xpTotal;

    public Aluno(String nome, String email, String nomePersonagem) {
        super(nome, email);
        this.personagem = new Personagem(nomePersonagem);
        this.xpTotal = 0;
    }

    public void completarMissao(Missao missao) {
        int xp = missao.getRecompensa().getXp();
        personagem.ganharXp(xp);
        xpTotal += xp;
        System.out.println(nome + " completou a missão: " + missao.getTitulo() + " e ganhou " + xp + " XP!");
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public int getXpTotal() {
        return xpTotal;
    }
}

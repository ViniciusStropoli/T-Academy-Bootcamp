package Exemplos;

public class Monstro extends Personagem{
    public int periculosidade;

    public Monstro(String nome, int vida, int defesa, int periculosidade) {
        super(nome, vida, defesa);
        this.periculosidade = periculosidade;
    }

    @Override
    public void atacar(Personagem personagem) {
        System.out.println("Monstro atacando");
        personagem.setVida(personagem.getVida() - this.periculosidade);
        System.out.println("Vida do inimigo" + getVida());
    }
}

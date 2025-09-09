package Exemplos;

public class Mago extends Personagem{

    public int mana;

    public Mago(String nome) {
        super(nome, 12, 3);
    }

    @Override
    public void atacar(Personagem personagem) {
        mana -= 1;
        System.out.println("Mago atacando");
        System.out.println("Mana atual: " + mana);
        System.out.println("Vida do inimigo" + getVida());
    }
}

package Exemplos;

public class Arqueiro extends Personagem{
    public int flechas;

    public Arqueiro(String nome, int vida, int defesa, int flechas) {
        super(nome, vida, defesa);
        this.flechas = flechas;
    }

    @Override
    public void atacar(Personagem personagem) {
        flechas -= 1;
        System.out.println("Mago atacou");
        System.out.println("Flechas: " + flechas);
        System.out.println("Vida do inimigo" + getVida());
    }
}

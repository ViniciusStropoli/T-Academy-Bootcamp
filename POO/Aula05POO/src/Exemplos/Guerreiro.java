package Exemplos;

public class Guerreiro extends Personagem {

    public int stamina;

    public Guerreiro(String nome) {
        super(nome, 20, 6);
    }

    @Override
    public void atacar(Personagem personagem) {
        stamina++;
        System.out.println("Guerreiro atacou");
        personagem.setVida(personagem.getVida() - 10);
        System.out.println("Stamina: " + stamina);
        System.out.println("Vida do inimigo" + getVida());
    }
}

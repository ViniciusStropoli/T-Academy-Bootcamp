package Exemplo01;

public class Libelula extends Animal implements Voador {
    private double velocidade;

    public Libelula(String especie, String cor) {
        super(especie, cor);
    }

    public Libelula(String especie, String cor, double velocidade) {
        super(especie, cor);
        this.velocidade = velocidade;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void voar() {

    }

    @Override
    public void pousar() {

    }
}

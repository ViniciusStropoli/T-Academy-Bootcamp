package Exemplo01;

public class Ornitorrinco extends Animal implements Nadador{

    @Override
    public void nadar() {}

    @Override
    public void mergulhar() {}

    public Ornitorrinco(String especie, String cor) {
        super(especie, cor);
    }
}

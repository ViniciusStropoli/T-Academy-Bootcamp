package Questao01;

public class Televisao extends Eletronico implements Conectavel, Ligavel{
    private int id;
    private boolean isLigado;
    private boolean hasWifi;

    public Televisao(String cor, String modelo, int id, boolean ligado) {
        super(cor, modelo);
        this.id = id;
        this.isLigado = ligado;
    }

    @Override
    public void conectar() {
        if(!hasWifi) {
            System.out.println("Conectando-se a internet...");
            hasWifi = true;
            System.out.println("Conectado com sucesso!");
        } else System.out.println("O dispositivo já está em uma wifi!");
    }

    @Override
    public void ligar() {
        if (!isLigado) {
            isLigado = true;
            System.out.println("O dispositivo foi ligado com sucesso");
        } else System.out.println("O dispositivo já está ligado");
    }

    @Override
    public void desligar() {
        if (isLigado) {
            isLigado = false;
            System.out.println("O dispositivo foi desligado com sucesso");
        } else System.out.println("O dispositivo já está desligado");
    }
}

package Questao01;

public class LampadaInteligente extends Eletronico implements Ligavel, Configuravel{
    private int id;
    private boolean isLigado;
    private boolean hasWifi;

    public LampadaInteligente(String cor, String modelo, int id, boolean isLigado, boolean hasWifi) {
        super(cor, modelo);
        this.id = id;
        this.isLigado = isLigado;
        this.hasWifi = hasWifi;
    }

    @Override
    public void configurar(boolean wifi) {
        if (wifi) {
            System.out.println("Dispositivo conectado a internet, iniciando configuração...");
            System.out.println("Configurando Smartphone...");
            System.out.println("Smartphone configurado com sucesso!");
        }else System.out.println("Conecte-se ao wifi e tente novamente");
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

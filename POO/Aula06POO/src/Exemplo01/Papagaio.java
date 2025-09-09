package Exemplo01;

public class Papagaio extends Animal implements Voador{
    private String fala;

    public Papagaio(String especie, String cor, String fala) {
        super(especie, cor);
        this.fala = fala;
    }

    public String getFala() {
        return fala;
    }

    public void setFala(String fala) {
        this.fala = fala;
    }

    @Override
    public void voar() {

    }

    @Override
    public void pousar() {

    }
}

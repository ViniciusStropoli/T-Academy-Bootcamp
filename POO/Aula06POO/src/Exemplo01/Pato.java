package Exemplo01;

public class Pato extends Animal implements Nadador {
    private String tipoQuack;

    public Pato(String especie, String cor) {
        super(especie, cor);
    }

    public Pato(String especie, String cor, String tipoQuack) {
        super(especie, cor);
        this.tipoQuack = tipoQuack;
    }

    public String getTipoQuack() {
        return tipoQuack;
    }

    public void setTipoQuack(String tipoQuack) {
        this.tipoQuack = tipoQuack;
    }

    @Override
    public void nadar() {

    }

    @Override
    public void mergulhar() {

    }
}

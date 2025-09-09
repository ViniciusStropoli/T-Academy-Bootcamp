package Exemplo01;

public class Gaivota extends Pato implements Voador, Nadador, Pescador{

    public Gaivota(String especie, String cor) {
        super(especie, cor);
    }

    public Gaivota(String especie, String cor, String tipoQuack) {
        super(especie, cor, tipoQuack);
    }

    @Override
    public void pescar() {}

    @Override
    public void mergulhar() {}

    @Override
    public void voar() {}

    @Override
    public void nadar() {}

    @Override
    public void pousar(){}

}

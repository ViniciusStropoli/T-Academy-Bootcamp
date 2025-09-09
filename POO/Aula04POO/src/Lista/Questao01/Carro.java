package Lista.Questao01;

public class Carro extends Veiculo {
    private String modelo;
    private String cor;

    @Override
    public String mostrarDetalhes() {
        return super.mostrarDetalhes() + "Modelo: "+ modelo+ " Cor: "+cor;
    }

    public Carro(String marca, int ano, String modelo, String cor) {
        super(marca, ano);
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {}
}

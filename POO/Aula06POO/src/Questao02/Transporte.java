package Questao02;

public abstract class Transporte {
    protected String marca;
    protected String modelo;
    protected String cor;
    protected int gasolina;

    public Transporte(String marca, String modelo, String cor, int gasolina) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.gasolina = gasolina;
    }

    public abstract void abastecer();

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getCor() {return cor;}

    public void setCor(String cor) {this.cor = cor;}

    public int getGasolina() {return gasolina;}

    public void setGasolina(int gasolina) {this.gasolina = gasolina;}
}

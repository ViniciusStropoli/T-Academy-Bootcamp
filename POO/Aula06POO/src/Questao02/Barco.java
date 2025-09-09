package Questao02;

public class Barco extends Transporte implements Aquatico{
    private int id;
    private int contador;

    public Barco(String marca, String modelo, String cor, int id, int contador, int gasolina) {
        super(marca, modelo, cor, gasolina);
        this.id = id;
        this.contador = contador;
        this.gasolina = gasolina;
    }

    @Override
    public void navegar() {
        if (gasolina > 0) {
            System.out.println("O barco está com combustível");
            System.out.println("Você navega nos mares e rios com seu barco!");
        } else System.out.println("O barco está sem gasolina!");
    }

    @Override
    public void abastecer() {
        this.gasolina = 200;
        System.out.println("Hidroavião abastecido com 200 litros.");
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public int getContador() {return contador;}

    public void setContador(int contador) {this.contador = contador;}
}

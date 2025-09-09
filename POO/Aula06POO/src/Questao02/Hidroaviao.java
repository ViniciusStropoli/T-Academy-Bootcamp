package Questao02;

public class Hidroaviao extends Transporte implements Aereo, Aquatico, Terrestre {
    private int id;
    private int contador;
    private String local;

    public Hidroaviao(String marca, String modelo, String cor, int id, int contador, int gasolina) {
        super(marca, modelo, cor, gasolina);
        this.id = id;
        this.contador = contador;
    }

    @Override
    public void voar() {
        if (gasolina > 0) {
            System.out.println("O hidroavião " + getModelo() + " está voando pelos céus.");
        } else {
            System.out.println("O hidroavião está sem combustível para voar.");
        }
    }

    @Override
    public void navegar() {
        if (gasolina > 0) {
            System.out.println("O hidroavião " + getModelo() + " está navegando na água.");
        } else {
            System.out.println("O hidroavião está sem combustível para navegar.");
        }
    }

    @Override
    public void dirigir() {
        if (gasolina > 0) {
            System.out.println("O hidroavião " + getModelo() + " está se movendo em solo.");
        } else {
            System.out.println("O hidroavião está sem combustível para se mover.");
        }
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

package Questao02;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Carro carro = new Carro("Toyota", "Corolla", "Prata", 1, 50, 0);
        Barco barco = new Barco("Yamaha", "WaveRunner", "Azul", 2, 0, 40);
        Hidroaviao hidroaviao = new Hidroaviao("Cessna", "172 Amphibian", "Branco", 3, 0, 60);

        carro.abastecer();
        barco.abastecer();
        hidroaviao.abastecer();

        List<Transporte> veiculos = new ArrayList<>();
        veiculos.add(carro);
        veiculos.add(barco);
        veiculos.add(hidroaviao);

        for (Transporte t : veiculos) {
            System.out.println("\n--- " + t.getModelo() + " ---");

            if (t instanceof Terrestre) {
                ((Terrestre) t).dirigir();
            }

            if (t instanceof Aquatico) {
                ((Aquatico) t).navegar();
            }

            if (t instanceof Aereo) {
                ((Aereo) t).voar();
            }
        }
    }
}

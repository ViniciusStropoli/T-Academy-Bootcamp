package Questao02;

import java.time.LocalTime;

public class Transporte {

    public String modelo;
    public Modais modal;
    public LocalTime horarioPartida;
    public LocalTime horarioChegada;


    public Transporte(String modelo, Modais modal) {
        this.modelo = modelo;
        this.modal = modal;
    }

    public enum Modais {
        ONIBUS, METRO, BICICLETAS;
    }

    public boolean verificarAtraso(LocalTime horarioAtual) {
        return horarioAtual.isAfter(horarioPartida);
    }
}
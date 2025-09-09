package Questao02;

public class Passagem {

    private Transporte modal;
    private String destino;
    private double preco;
    private String horarioChegada;
    private String horarioSaida;

    public Passagem(Transporte modal, String destino, double preco, String horarioChegada, String horarioSaida) {
        this.modal = modal;
        this.destino = destino;
        this.preco = preco;
        this.horarioChegada = horarioChegada;
        this.horarioSaida = horarioSaida;
    }

    public Transporte getModal() {
        return modal;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(String horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}

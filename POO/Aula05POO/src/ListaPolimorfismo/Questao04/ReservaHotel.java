package ListaPolimorfismo.Questao04;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservaHotel {
    private String nomeCliente;
    private double valorDiaria;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public ReservaHotel(String nomeCliente, double valorDiaria, LocalDate dataEntrada, LocalDate dataSaida) {
        this.nomeCliente = nomeCliente;
        this.valorDiaria = valorDiaria;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public void calcularTotal() {
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        double total = dias * valorDiaria;
        System.out.println("Valor total da estadia de "+this.nomeCliente+": R$ " + total);
    }

    public void calcularTotal(double desconto) {
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        double total = dias * valorDiaria;
        total *= (1 - desconto / 100);
        System.out.println("Valor total da estadia de "+this.nomeCliente+" com desconto: R$ " + total);
    }

    public String getNomeCliente() {return nomeCliente;}

    public void setNomeCliente(String nomeCliente) {this.nomeCliente = nomeCliente;}

    public double getValorDiaria() {return valorDiaria;}

    public void setValorDiaria(double valorDiaria) {this.valorDiaria = valorDiaria;}

    public LocalDate getDataEntrada() {return dataEntrada;}

    public void setDataEntrada(LocalDate dataEntrada) {this.dataEntrada = dataEntrada;}

    public LocalDate getDataSaida() {return dataSaida;}

    public void setDataSaida(LocalDate dataSaida) {this.dataSaida = dataSaida;}
}

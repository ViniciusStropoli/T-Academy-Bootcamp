package ListaPolimorfismo.Questao04;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate entrada = LocalDate.of(2025, 9, 10);
        LocalDate saida = LocalDate.of(2025, 9, 15);

        ReservaHotel reserva = new ReservaHotel("Andrey ", 200.0, entrada, saida);

        reserva.calcularTotal();
        reserva.calcularTotal(10.0);
    }
}

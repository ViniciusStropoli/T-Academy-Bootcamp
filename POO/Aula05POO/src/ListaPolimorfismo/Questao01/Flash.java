package ListaPolimorfismo.Questao01;

import java.util.Arrays;

public class Flash extends Heroi {

    public Flash() {
        super("Flash", "Mais rápido que a luz!",
                Arrays.asList("Super Velocidade", "Viajar no Tempo", "Criação de Vórtices"));
    }

    @Override
    public void agirEmMissao() {
        apresentar();
        usarPoder();
        System.out.println("💨 Flash salvou a cidade antes que o tempo acabasse!\n");
    }
}

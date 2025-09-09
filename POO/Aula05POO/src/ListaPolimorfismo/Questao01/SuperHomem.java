package ListaPolimorfismo.Questao01;

import java.util.Arrays;

public class SuperHomem extends Heroi {

    public SuperHomem() {
        super("Super-Homem", "Com grandes poderes, vem grandes responsabilidades!",
                Arrays.asList("Super Força", "Visão de Calor", "Voo", "Super Audição"));
    }

    @Override
    public void agirEmMissao() {
        apresentar();
        usarPoder();
        System.out.println("🛡️ Super-Homem protegeu a cidade com coragem!\n");
    }
}

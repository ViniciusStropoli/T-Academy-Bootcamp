package ListaPolimorfismo.Questao01;

import java.util.Arrays;

public class MulherMaravilha extends Heroi {

    public MulherMaravilha() {
        super("Mulher-Maravilha", "A verdade é minha arma!",
                Arrays.asList("Laço da Verdade", "Força Amazona", "Escudo Indestrutível"));
    }

    @Override
    public void agirEmMissao() {
        apresentar();
        usarPoder();
        System.out.println("🗡️ Mulher-Maravilha derrotou seus inimigos com honra!\n");
    }
}

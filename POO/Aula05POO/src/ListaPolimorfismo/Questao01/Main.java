package ListaPolimorfismo.Questao01;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Heroi> ligaDaJustica = new ArrayList<>();

        ligaDaJustica.add(new SuperHomem());
        ligaDaJustica.add(new MulherMaravilha());
        ligaDaJustica.add(new Flash());

        System.out.println("🎬 Episódio de Hoje: A Ameaça Invisível\n");
        Thread.sleep(1000);

        for (Heroi heroi : ligaDaJustica) {
            heroi.agirEmMissao();
            Thread.sleep(1500);
        }

        System.out.println("🏁 Missão cumprida! A cidade está segura graças à Liga da Justiça!");
    }
}

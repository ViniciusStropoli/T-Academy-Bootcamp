package ListaPolimorfismo.Questao02;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        Produto p1 = new Produto("Banana", 10, 100);
        Produto p2 = new Produto("Maça", 15, 150);
        Produto p3 = new Produto("Papel", 20, 50);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);

        p1.adicionarEstoque(10);
        p2.adicionarEstoque(20, 2);
        p3.adicionarEstoque(200, 10);
    }
}


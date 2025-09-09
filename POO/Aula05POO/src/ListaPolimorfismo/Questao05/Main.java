package ListaPolimorfismo.Questao05;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> carrinho = new ArrayList<>();

        carrinho.add(new ProdutoFisico("Livro", 2, 50.0, 10.0));
        carrinho.add(new ProdutoDigital("E-book", 1, 30.0));
        carrinho.add(new ProdutoFisico("Mouse", 1, 80.0, 15.0));
        carrinho.add(new ProdutoDigital("Curso Online", 3, 100.0));

        double totalCompra = 0.0;

        for (Item item : carrinho) {
            double precoItem = item.calcularPreco();
            System.out.println("Item: " + item.getNome() + " | Preço calculado: R$" + precoItem);
            totalCompra += precoItem;
        }

        System.out.println("Preço total da compra: R$" + totalCompra);
    }
}

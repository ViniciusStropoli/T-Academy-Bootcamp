package Exercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class Exe03 {

    static ArrayList<String> produtos= new ArrayList<>();
    static ArrayList<Double> precos = new ArrayList<>();
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("(1) - Novo produto");
            System.out.println("(2) - Ver produtos");
            System.out.println("(0) - Sair");
            System.out.println("Opção: ");
            int op = s.nextInt();

            if(op == 0) break;

            switch (op) {
                case 1 -> cadastrarNovoProduto();
                case 2 -> mostrarProdutos();
                default -> System.out.println("Inválido");
            }
        }
    }

    public static void cadastrarNovoProduto() {
        System.out.println("Nome do produto: ");
        produtos.add(s.next());

        System.out.println("Preço: R$ ");
        precos.add(s.nextDouble());
    }

    public static void mostrarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i+1) + " - " + produtos.get(i) + " R$" + precos.get(i));
        }
    }
}

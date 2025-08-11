package Exercicios;

import java.util.Scanner;

public class Exe02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Quantos preços você quer cadastrar? ");
        int num = s.nextInt();

        double[] precos = new double[num];
        String[] produto = new String[num];

        for (int i = 0; i < produto.length; i++) {
            System.out.println("\nProduto "+ i +": ");
            produto[i] = s.next();
            System.out.println("Preço "+ i +": ");
            precos[i] = s.nextInt();
        }


        System.out.println("Qual preço queres verificar? ");
        int qual = s.nextInt();

        System.out.println("O preço do " + produto[qual] + " é: R$ " + precos[qual]);

        s.close();
    }
}

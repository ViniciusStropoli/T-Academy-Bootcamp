package Questões;

import java.util.Scanner;

public class Exer27LE02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double precoMorangoAte5Kg = 2.50;
        double precoMorangoAcima5Kg = 2.20;
        double precoMacaAte5Kg = 1.80;
        double precoMacaAcima5Kg = 1.50;

        double desconto = 0.10;

        System.out.print("Digite a quantidade de morangos (em Kg): ");
        double kgMorango = scanner.nextDouble();

        System.out.print("Digite a quantidade de maçãs (em Kg): ");
        double kgMaca = scanner.nextDouble();

        double precoMorango = 0;
        if (kgMorango <= 5) precoMorango = kgMorango * precoMorangoAte5Kg;
        else precoMorango = kgMorango * precoMorangoAcima5Kg;

        double precoMaca = 0;
        if (kgMaca <= 5) precoMaca = kgMaca * precoMacaAte5Kg;
        else precoMaca = kgMaca * precoMacaAcima5Kg;

        double total = precoMorango + precoMaca;

        if (kgMorango + kgMaca > 8 || total > 25.00) total -= total * desconto;

        System.out.printf("O valor total a ser pago é: R$ "+ total);

        scanner.close();
    }
}
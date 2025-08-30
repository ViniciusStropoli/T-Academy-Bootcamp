package Questões;
import java.util.Scanner;

public class Exe28LE02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double precoFileAte5Kg = 4.90;
        double precoFileAcima5Kg = 5.80;

        double precoAlcatraAte5Kg = 5.90;
        double precoAlcatraAcima5Kg = 6.80;

        double precoPicanhaAte5Kg = 6.90;
        double precoPicanhaAcima5Kg = 7.80;

        double descontoCartao = 0.05;

        System.out.println("Bem-vindo ao Hipermercado Tabajara!");
        System.out.print("Escolha o tipo de carne (File Duplo / Alcatra / Picanha): ");
        String tipoCarne = s.nextLine().toLowerCase().trim();

        if (!tipoCarne.equals("file duplo") && !tipoCarne.equals("alcatra") && !tipoCarne.equals("picanha")) {
            System.out.println("Tipo de carne inválido! Tente novamente.");
            return;
        }

        System.out.print("Digite a quantidade de carne (em Kg): ");
        double quantidadeKg = s.nextDouble();

        double precoPorKg = 0;
        if (tipoCarne.equals("file duplo")) {
            if (quantidadeKg <= 5) {
                precoPorKg = precoFileAte5Kg;
            } else {
                precoPorKg = precoFileAcima5Kg;
            }
        } else if (tipoCarne.equals("alcatra")) {
            if (quantidadeKg <= 5) {
                precoPorKg = precoAlcatraAte5Kg;
            } else {
                precoPorKg = precoAlcatraAcima5Kg;
            }
        } else if (tipoCarne.equals("picanha")) {
            if (quantidadeKg <= 5) {
                precoPorKg = precoPicanhaAte5Kg;
            } else {
                precoPorKg = precoPicanhaAcima5Kg;
            }
        }

        double valorTotal = precoPorKg * quantidadeKg;

        System.out.print("Deseja pagar com cartão Tabajara (s/n)? ");
        char tipoPagamento = s.next().charAt(0);

        double valorDesconto = 0;
        if (tipoPagamento == 's' || tipoPagamento == 'S') {
            valorDesconto = valorTotal * descontoCartao;
            valorTotal -= valorDesconto;
        }

        System.out.println("\n------------------- CUPOM FISCAL -------------------");
        System.out.println("Tipo de Carne: " + tipoCarne.substring(0, 1).toUpperCase() + tipoCarne.substring(1));
        System.out.println("Quantidade: " + quantidadeKg + " Kg");
        System.out.println("Preço por Kg: R$ " + String.format("%.2f", precoPorKg));
        System.out.println("Preço Total: R$ " + String.format("%.2f", precoPorKg * quantidadeKg));
        if (tipoPagamento == 's' || tipoPagamento == 'S') {
            System.out.println("Desconto Cartão Tabajara: -R$ " + String.format("%.2f", valorDesconto));
        }
        System.out.println("Valor a Pagar: R$ " + String.format("%.2f", valorTotal));
        System.out.println("------------------------------------------------------");

        s.close();
    }
}
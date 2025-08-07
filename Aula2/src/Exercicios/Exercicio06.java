package Exercicios;
import java.util.Scanner;

public class Exercicio06 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite um ano: ");
        int ano = s.nextInt();

        if ((ano % 4 == 0) && (ano % 100 == 0) && (ano % 400 == 0)) {
            System.out.println("É ano bissexto");
        } else if (((ano % 4) == 0) && ((ano % 100) == 0)) {
            System.out.println("Não é bissexto");
        }

        s.close();
    }
}

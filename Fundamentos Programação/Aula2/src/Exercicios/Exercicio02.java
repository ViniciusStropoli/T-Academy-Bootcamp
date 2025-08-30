package Exercicios;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite um número: ");
        int numero = s.nextInt();
        System.out.println((numero % 2 == 0) ? "Par" : "Ímpar");

        s.close();
    }
}

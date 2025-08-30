package Exercicios;

import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int numero = s.nextInt();
        if (numero > 0) System.out.println("Positivo");
        else if (numero < 0) System.out.println("Negativo");
        else System.out.println("Zero");


        s.close();
    }
}
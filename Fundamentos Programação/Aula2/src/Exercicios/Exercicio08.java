package Exercicios;
import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite um intervalo de numeros (a - - - b): ");
        int a = s.nextInt();
        int b = s.nextInt();

        System.out.println("Digite um numero: ");
        int numero = s.nextInt();

        if((numero > a) && (numero < b)) {
            System.out.println("O número está no intervalo!");
        } else {
            System.out.println("O número não está no intervalo");
        }

        s.close();
    }
}

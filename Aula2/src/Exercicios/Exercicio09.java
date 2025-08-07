package Exercicios;
import java.util.Scanner;

public class Exercicio09 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite os tres lados de um triângulo: ");
        int a = s.nextInt(), b = s.nextInt(), c = s.nextInt();

        if (a == b && b == c) {
            System.out.println("Triangulo equilatero");
        } else if ((a == b) || (a == c) || (b == c)) {
            System.out.println("Triangulo isósceles");
        } else {
            System.out.println("Triangulo escaleno");
        }

        s.close();
    }
}

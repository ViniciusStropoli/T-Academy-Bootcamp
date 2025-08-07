package Exercicios;
import java.util.Scanner;

public class Exe16Lista02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite os valores de a, b e c");
        double a = s.nextInt();

        if (a == 0) {
            System.out.println("Não é uma equação de segundo grau.");
            return;
        }
        double b = s.nextDouble(), c = s.nextDouble();

        double delta = Math.pow(b,2) - 4 * a * c;

        if (delta < 0) {
            System.out.println("O delta é menor que zero, a equação não possuí raizes reais.");
        } else if (delta == 0) {
            double raiz = -b/ 2 * a;
            System.out.println("Delta é igual a zero, então a unica raiz real é: "+raiz);
            return;
        } else {
            double x1 = (-b + Math.sqrt(delta)) / 2 * a;
            double x2 = (-b - Math.sqrt(delta)) / 2 * a;

            System.out.println("A equação possuí duas raízes reais: " + x1 + "e, " + x2);
        }

        s.close();
    }
}

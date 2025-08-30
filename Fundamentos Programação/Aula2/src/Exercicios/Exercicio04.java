package Exercicios;
import java.util.Scanner;

public class Exercicio04 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite um número: ");
        int numero = s.nextInt();

        if ((numero % 3 == 0) ||(numero % 5 == 0)) {
            System.out.println("É divisivel por 3 e por 5!");
        } else {
            System.out.println("Não é divisivel por 3 e 5.");
        }

        s.close();
    }
}

package Exercicios;
import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite sua idade: ");
        int idade = s.nextInt();

        if (idade > 65) {
            System.out.println("Pode se aposentar!");
        } else {
            System.out.println("Não pode se aposentar!");
        }

        s.close();
    }
}

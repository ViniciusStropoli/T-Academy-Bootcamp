package Exercicios;
import java.util.Scanner;

public class Exercicio03 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite sua idade: ");
        int idade = s.nextInt();
        if (idade < 30) {
            System.out.println("Jovem");
        } else if (idade > 30 && idade < 65) {
            System.out.println("Adulto ");
        } else {
            System.out.println("Idoso");
        }

        s.close();
    }
}

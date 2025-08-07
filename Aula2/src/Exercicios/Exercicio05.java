package Exercicios;
import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite sua nota: ");
        int nota = s.nextInt();
        System.out.println(nota > 60 ? "Aprovado" : "Reprovado");

        s.close();
    }
}

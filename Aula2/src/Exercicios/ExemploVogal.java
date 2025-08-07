package Exercicios;

import java.util.Scanner;

public class ExemploVogal {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite uma letra: ");
        String letra = s.next();

        if (letra.length() > 1) {
            System.out.println("Digite só um caracter.");
            System.exit(0);
        }

        if ("aeiou".contains(letra)) {
            System.out.println("É uma vogal! ☻");
        } else if ("0123456789".contains(letra)) System.out.println("Número!");
        else System.out.println("É uma consoante!");


        /*if (letra.equalsIgnoreCase("a") || letra.equalsIgnoreCase("e") || letra.equalsIgnoreCase("i") || letra.equalsIgnoreCase("o") || letra.equalsIgnoreCase("u")) {
            System.out.println(letra+" é uma vogal!");
        } else System.out.println("Não é uma vogal");*/

        s.close();
    }
}

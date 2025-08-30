package Exercicios;

import java.util.Scanner;

public class Exe01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Quantos animais você quer cadastrar?");
        int num = s.nextInt();

        String[] animais = new String[num];

        for (int i = 0; i < animais.length; i++) {
            System.out.print("\nNome do animal " + (i)+ ": ");
            animais[i] = s.next();
        }

        for (int i = 0; i < animais.length; i++) {
            System.out.println("Animal " + (i) + " é: " + animais[i]);
        }
    }
}

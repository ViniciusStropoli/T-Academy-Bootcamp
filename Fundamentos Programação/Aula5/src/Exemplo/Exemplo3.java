package Exemplo;

import java.util.ArrayList;
import java.util.Scanner;

public class Exemplo3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArrayList<String> frutas = new ArrayList<>();

        while(true) {
            System.out.println("Nome da fruta: (0 pra sair)");
            String fruta = s.next();

            if (fruta.equals("0")) break;

            frutas.add(fruta);
        }

        System.out.println("\n0FRUTAS \n");

      /*for (String fruta : frutas) {
            System.out.println(fruta);
        }*/

        for (int i = 0; i < frutas.size(); i++) {
            System.out.println(frutas.get(i));
        }
    }
}

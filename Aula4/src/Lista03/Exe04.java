package Lista03;

import java.util.Scanner;

public class Exe04 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double habA = 80000;
        double taxaCrescimentoA = 0.03;

        double habB = 200000;
        double taxaCrescimentoB = 0.015;

        int ano = 0;

        while (habA < habB) {

            habA += habA * taxaCrescimentoA;
            habB += habB * taxaCrescimentoB;

            ano++;
        }

        System.out.println("O ano em que a população de A passara a de B é: "+ ano + " anos.");
        System.out.println("A população final de A é: "+ habA);
        System.out.println("A população final de B é: "+ habB);

        s.close();
    }
}
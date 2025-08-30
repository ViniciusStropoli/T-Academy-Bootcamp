package Exemplo;

import java.util.Scanner;

public class Exemplo {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

      /*System.out.println("Nota 1:");
        double nota1 = s.nextDouble();

        System.out.println("Nota 2:");
        double nota2 = s.nextDouble();

        System.out.println("Nota 3:");
        double nota3 = s.nextDouble();

        System.out.println("Nota 4:");
        double nota4 = s.nextDouble();

        double media = ((nota1+nota2+nota3+nota4)/4);*/

      /*double[] notas = new double[4];

        notas[0] = 9.5;
        notas[1] = 6;
        notas[2] = 4.5;
        notas[3] = 10;

        System.out.println(notas[0]);
        System.out.println(notas[1]);
        System.out.println(notas[2]);
        System.out.println(notas[3]);*/


        System.out.println("Quantas notas voce quer cadastrar?");
        int num = s.nextInt();
        double[] notas = new double[num];


        for (int i = 0; i < num; i++) {
            System.out.println("Nota "+(i+1));
            notas[i] = s.nextDouble();
        }

        for (int i = 0; i < notas.length; i++) {
            System.out.println(notas[i]);
        }




        s.close();
    }
}

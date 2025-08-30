package Exercicios;
import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] numeros = new int[3];
        int n = numeros.length;

        for (int i = 0; i < 3; i++) {
            System.out.println("Digite o numero ("+i+"): ");
            numeros[i] = s.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (numeros[j] > numeros[j+1]) {
                    int temp = numeros[j];
                    numeros[j] = numeros[j+1];
                    numeros[j+1] = temp;
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            System.out.println(numeros[i]);
        }

        s.close();
    }
}

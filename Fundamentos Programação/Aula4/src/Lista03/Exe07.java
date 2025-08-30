package Lista03;

import java.util.Scanner;

public class Exe07 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] numeros = new int[5];
        int maior = 0;

        System.out.println("Digite 5 números");

        for (int i = 0; i < 5; i++) {
            System.out.print("Numero ("+i+"): ");
            numeros[i] = s.nextInt();

            if (numeros[i] > maior) {
                maior = numeros[i];
            }
        }
        System.out.println("O maior número digitado é: "+ maior);



        s.close();
    }
}

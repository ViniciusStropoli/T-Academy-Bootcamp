package Lista03;

import java.util.Scanner;

public class Exe15 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Digite o valor de n: ");
        int n = s.nextInt();

        System.out.println("Sequência de Fibonacci até o " + n + "º termo:");

        int a = 0, b = 1;

        if (n >= 1) {
            System.out.print(a + " ");
        }

        if (n >= 2) {
            System.out.print(b + " ");
        }

        for (int i = 3; i <= n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }

        s.close();
    }
}
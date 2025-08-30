package Exercicios;
import java.util.Scanner;

public class Exe11Lista02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite seu salário: ");
        double salario = s.nextDouble();
        double aumento;

        if(salario <= 280) aumento = 20;
        else if (salario > 280 && salario <= 700) aumento = 15;
        else if (salario > 700 && salario <= 1500) aumento = 10;
        else aumento = 5;

        System.out.println("Você recebeu um aumento de "+aumento+" porcento! ☻");
        System.out.println("Seu antigo salário era: R$ " + salario);
        System.out.println("Seu novo salário é: R$ "+(salario + salario * (aumento / 100)));

        s.close();
    }
}


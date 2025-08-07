package Exercicios;
import java.util.Scanner;

public class Exe12Lista02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double ir;
        double inss = 0.10;
        double fgts = 0.11;

        System.out.print("Digite o seu salario/hora: ");
        double salHora = s.nextDouble();
        System.out.print("Digite o número de horas trabalhadas no mês: ");
        int horas = s.nextInt();

        double salBruto = salHora * horas;

        if (salBruto > 900 && salBruto <= 1500) {
            ir = 5;
        } else if (salBruto > 1500 && salBruto < 2500) {
            ir = 10;
        } else {
            ir = 20;
        }

        double salIR = (salBruto * (ir/100));
        double salINSS = (salBruto * inss);
        double salFGTS = (salBruto * fgts);
        double totalDescontos = (salIR + salINSS);
        double salarioLiquido = salBruto - totalDescontos;


        System.out.println("Salario Bruto: "+salBruto);
        System.out.println("IR ("+ir+"%):   "+salIR);
        System.out.println("INSS (10%):  "+ salINSS);
        System.out.println("FGTS (11%):   "+ salFGTS);
        System.out.println("Total de descontos: "+ totalDescontos);
        System.out.println("Salário liquido: " + salarioLiquido);

        s.close();
    }
}

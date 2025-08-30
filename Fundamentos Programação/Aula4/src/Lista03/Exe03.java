package Lista03;

import java.util.Scanner;

public class Exe03 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String nome = "";
        int idade = 0;
        double salario = 0;
        String sexo = "";
        String estaCivil = "";

        do {
            System.out.println("Nome: ");
            nome = s.next();
        }while (nome.length() <= 3);

        do {
            System.out.println("Idade: ");
            idade = s.nextInt();
        } while (idade > 0 && idade < 150);

        do {
            System.out.println("Salário: ");
        } while (salario != 0);

        do {
            System.out.println("Sexo: ");
        } while (!(sexo.equals("s")) && !(sexo.equals("m")));

        do {
            System.out.println("Estado cívil");
        } while (!estaCivil.equals("s") && !estaCivil.equals("c") && !estaCivil.equals("v") && !estaCivil.equals("d"));

        s.close();
    }
}

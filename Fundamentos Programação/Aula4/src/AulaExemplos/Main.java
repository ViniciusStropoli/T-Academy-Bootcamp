package AulaExemplos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while(true) {

            System.out.println("1 - cadastro de cliente");
            System.out.println("2 - cadastrar pet");
            System.out.println("3 - comprar pet");
            System.out.println("0 - Sair do sistema");
            System.out.println("\nOpção: ");
            String menu = s.next();

            switch (menu) {
                case "0": {
                    System.out.println("Saindo do sistema...");
                    break;
                }
                case "1": {
                    System.out.println("Cadastro");
                    break;
                }
                case "2": {
                    System.out.println("Cadastro pet");
                    break;
                }
                case "3": {
                    System.out.println("Comprar pet");
                    break;
                }
                default: {
                    System.out.println("Entrada inválida");
                    break;
                }
            }


        }

    }
}

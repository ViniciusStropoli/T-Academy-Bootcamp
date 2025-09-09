import Controller.GarrafaController;
import Model.Garrafa;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GarrafaController controller = new GarrafaController();
        Scanner s = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar garrafa");
            System.out.println("2. Buscar garrafa por ID");
            System.out.println("3. Listar todas as garrafas");
            System.out.println("4. Buscar garrafas por NOME");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = s.nextInt();

            switch (opcao) {
                case 1:
                    s.nextLine();
                    System.out.print("Nome: ");
                    String nome = s.nextLine();
                    System.out.print("Cor: ");
                    String cor = s.nextLine();
                    System.out.print("Capacidade (em litros): ");
                    double capacidade = s.nextDouble();

                    controller.adicionarGarrafa(nome, cor, capacidade);
                    break;

                case 2:
                    System.out.print("Digite o ID da garrafa: ");
                    int id = s.nextInt();
                    Garrafa g = controller.buscarPorId(id);
                    if (g != null) {
                        System.out.println("Encontrada: " + g);
                    } else {
                        System.out.println("Garrafa com ID " + id + " não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Todas as garrafas:");
                    for (Garrafa garrafa : controller.listarTodas()) {
                        System.out.println(garrafa);
                    }
                    break;

                case 4:
                    s.nextLine();
                    System.out.print("Digite o nome (ou parte): ");
                    String buscaNome = s.nextLine();
                    List<Garrafa> encontradas = controller.buscarPorNome(buscaNome);
                    if (encontradas.isEmpty()) {
                        System.out.println("Nenhuma garrafa encontrada com o nome: " + buscaNome);
                    } else {
                        System.out.println("Garrafas encontradas:");
                        for (Garrafa garrafa : encontradas) {
                            System.out.println(garrafa);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        s.close();
    }
}

package sistemaConsulta; // Dupla: Andrey Carlos e Vinícius Stropoli.

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Paciente paciente = null;

        int opcao;

        do {
            System.out.println("\n=== Sistema de Consulta Médica ===");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Calcular IMC");
            System.out.println("3 - Iniciar Consulta");
            System.out.println("4 - Agendar Consulta");
            System.out.println("5 - Exibir Dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();
            s.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = s.nextLine();

                    System.out.print("Digite o peso: ");
                    double peso = s.nextDouble();

                    System.out.print("Digite a altura: ");
                    double altura = s.nextDouble();
                    s.nextLine();

                    paciente = new Paciente(nome, peso, altura);
                    System.out.println("Paciente cadastrado com sucesso!");
                    break;

                case 2:
                    if (paciente != null) {
                        double imc = paciente.calcularImc();
                        System.out.printf("IMC de %s: %.2f\n", paciente.getNome(), imc);
                    } else {
                        System.out.println("Nenhum paciente cadastrado!");
                    }
                    break;

                case 3:
                    if (paciente != null) iniciarConsulta(paciente, s);
                    else System.out.println("Cadastre um paciente primeiro!");
                    break;

                case 4:
                    if (paciente != null) {
                        System.out.print("Digite a data da consulta (dd/mm/aaaa): ");
                        String data = s.nextLine();

                        System.out.print("Digite o horário da consulta (hh:mm): ");
                        String hora = s.nextLine();

                        Consulta consulta = new Consulta(data, hora);
                        paciente.agendarConsulta(consulta);

                        System.out.println("Consulta agendada com sucesso!");
                        consulta.exibirConsulta();
                    } else {
                        System.out.println("Cadastre um paciente primeiro!");
                    }
                    break;

                case 5:
                    if (paciente != null) paciente.exibirDados();
                    else System.out.println("Nenhum paciente adicionado ainda.");
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        s.close();
    }

    public static void iniciarConsulta(Paciente paciente, Scanner s) {
        String[] perguntas = {
                "Está sentindo febre?",
                "Tem dores no corpo?",
                "Está com tosse?",
                "Percebe cansaço ou falta de ar?",
                "Perdeu o apetite recentemente?"
        };

        int respostasSim = 0;

        System.out.println("\nIniciando consulta com " + paciente.getNome() + "...");

        for (String pergunta : perguntas) {
            System.out.print(pergunta + " (sim/nao): ");
            String resposta = s.nextLine().toLowerCase();

            if (resposta.equals("sim")) {
                respostasSim++;
            }
        }

        System.out.println("\n--- Diagnóstico ---");
        if (respostasSim >= 4) System.out.println("Diagnóstico: Possível infecção grave. Procurar atendimento médico.");
        else if (respostasSim >= 2) System.out.println("Diagnóstico: Sintomas leves. Monitorar em casa.");
        else System.out.println("Diagnóstico: Sem indícios preocupantes.");

    }
}
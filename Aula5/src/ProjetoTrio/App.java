package ProjetoTrio;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static Scanner s = new Scanner(System.in);

    static ArrayList<String> nomeNaves = new ArrayList<>();
    static ArrayList<Integer> quantidadeTripulantes = new ArrayList<>();
    static ArrayList<String> tripulantes = new ArrayList<>();
    static String nomeNave;
    static int quantidadePessoasNave;
    static String galaxiaEscolhida;
    static String planetaEscolhido;

    public App() {
        while (true) {
            System.out.println("Quer começar a viagem? (s/n)");
            String escolha = s.nextLine().toLowerCase();
            if (escolha.equals("n")) break;
            else if (escolha.equals("s")) escolherGalaxia();
            else System.out.println("Informação inválida");
        }
    }

    static void escolherPlaneta(int galaxia) {
        ArrayList<String> planetas = new ArrayList<>();
        if (galaxia == 1) {
            planetas.add("1 - Javópolis");
            planetas.add("2 - MySequelópolis");
            planetas.add("3 - Planeta-123");
            galaxiaEscolhida = "Andrômeda";
        } else if (galaxia == 2) {
            planetas.add("1 - Planeta-312");
            planetas.add("2 - Plutão-2");
            planetas.add("3 - Terra-Alternativa");
            galaxiaEscolhida = "Grande Nuvem de Magalhães";
        } else {
            System.out.println("Galáxia inválida.");
            return;
        }

        System.out.println("Nesta galáxia existem 3 planetas visitáveis:");
        for (String planeta : planetas) {
            System.out.println(planeta);
        }

        int escolhaPlaneta = s.nextInt();
        s.nextLine(); // Consumir a quebra de linha
        if (escolhaPlaneta >= 1 && escolhaPlaneta <= planetas.size()) {
            planetaEscolhido = planetas.get(escolhaPlaneta - 1).split(" - ")[1];
        } else {
            System.out.println("Opção inválida.");
        }

        adicionarNave();
    }

    static void adicionarNave() {
        while (true) {
            System.out.print("Adicione o nome da nave ou 0 para sair: ");
            String nave = s.nextLine();
            if (nave.equalsIgnoreCase("0")) break;

            System.out.print("Digite a quantidade de pessoas na nave: ");
            int qtdPessoasNave = s.nextInt();
            s.nextLine(); // Consumir a quebra de linha

            if (qtdPessoasNave > 0) {
                nomeNaves.add(nave);
                quantidadeTripulantes.add(qtdPessoasNave);
                System.out.println("Nave adicionada com sucesso.");
            } else {
                System.out.println("Quantidade de pessoas inválida. Tente novamente.");
            }

            System.out.println("Deseja parar de adicionar naves e ir para a próxima etapa? (s/n)");
            String escolha = s.nextLine().toLowerCase();
            if (escolha.equals("s")) {
                escolherNave();
                return;
            } else if (!escolha.equals("n")) {
                System.out.println("Escolha inválida!");
            }
        }
    }

    static void escolherNave() {
        for (int i = 0; i < nomeNaves.size(); i++) {
            System.out.println("Nome da nave: " + nomeNaves.get(i) + " - Espaço: " + quantidadeTripulantes.get(i) + " tripulantes");
        }

        System.out.print("\nEscolha a sua nave pelo nome: ");
        String escolhaNave = s.nextLine();

        for (int i = 0; i < nomeNaves.size(); i++) {
            if (nomeNaves.get(i).equalsIgnoreCase(escolhaNave)) {
                nomeNave = nomeNaves.get(i);
                quantidadePessoasNave = quantidadeTripulantes.get(i);
                System.out.println("Você escolheu a nave: " + nomeNave + " com capacidade para " + quantidadePessoasNave + " tripulantes.");
                cadastroTripulantes();
                return;
            }
        }
        System.out.println("Nave não encontrada.");
    }

    static void cadastroTripulantes() {
        System.out.println("Iniciando cadastro da tripulação:");
        for (int i = 0; i < quantidadePessoasNave; i++) {
            System.out.print("Cadastre o " + (i + 1) + "º tripulante: ");
            String tripulante = s.nextLine();
            tripulantes.add(tripulante);
            System.out.println("Tripulante " + (i + 1) + ": " + tripulante);
        }
        todosDadosViagem();
    }

    static void todosDadosViagem() {
        System.out.println("\n--- Dados da Viagem ---");
        System.out.println("Galáxia: " + galaxiaEscolhida);
        System.out.println("Planeta: " + planetaEscolhido);
        System.out.println("Nave: " + nomeNave);
        System.out.println("Quantidade de passageiros: " + quantidadePessoasNave);
        System.out.println("Tripulantes:");
        for (String tripulante : tripulantes) {
            System.out.println("- " + tripulante);
        }
        System.out.println();
        System.out.println("Você chegou ao seu destino!");

        System.out.println("            /\\");
        System.out.println("           /  \\");
        System.out.println("          /    \\");
        System.out.println("    /\\   /      \\   /\\");
        System.out.println("   /  \\ /        \\ /  \\");
        System.out.println("  /    \\          /    \\");
        System.out.println(" /______\\________/______\\");
        System.out.println(" \\      /\\      /\\      /");
        System.out.println("  \\    /  \\    /  \\    /");
        System.out.println("   \\  /    \\  /    \\  /");
        System.out.println("    \\/      \\/      \\/");
        System.out.println("     \\      /\\      /");
        System.out.println("      \\    /  \\    /");
        System.out.println("       \\  /    \\  /");
        System.out.println("        \\/      \\/");


        System.exit(0);
    }

    static void escolherGalaxia() {
        System.out.println("\nEscolha uma das galáxias colonizadas:");
        String[] galaxias = {"Andrômeda", "Grande Nuvem de Magalhães"};

        for (int i = 0; i < galaxias.length; i++) {
            System.out.println("(" + (i + 1) + ") " + galaxias[i]);
        }

        System.out.print("\nEscolha o índice da galáxia: ");
        int galaxia = s.nextInt();
        s.nextLine();

        escolherPlaneta(galaxia);
    }

    public static void main(String[] args) {
        new App();
    }
}

package Trabalho;

import java.util.*;

public class Menu {

    static Scanner sc = App.sc;
    static HashMap<String, Item> catalogo = App.catalogo;
    static ArrayList<Receita> receitas = App.receitas;

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String WHITE_BOLD = "\u001B[1;37m";
    private static final String RED = "\u001B[31m";


    public static void menuPrincipal() {
        while (true) {
            System.out.println(CYAN + "\n╔════════════════════════════════╗");
            System.out.println("║     " + WHITE_BOLD + "=== MENU PRINCIPAL ===" + CYAN + "     ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ " + YELLOW + "(1)" + RESET + " Gerenciar Catálogo (Itens) " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(2)" + RESET + " Gerenciar Estoque          " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(3)" + RESET + " Gerenciar Receitas         " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(4)" + RESET + " Operações de Engenharia    " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(0)" + RESET + " Sair                       " + CYAN + "║");
            System.out.println("╚════════════════════════════════╝" + RESET);
            System.out.print(GREEN + "Escolha uma opção: " + RESET);

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0 -> { return; }
                case 1 -> menuCatalogo();
                case 2 -> menuEstoque();
                case 3 -> menuReceitas();
                case 4 -> menuOperacoes();
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static void menuCatalogo() {
        while (true) {
            System.out.println(CYAN + "\n╔════════════════════════════╗");
            System.out.println("║     " + WHITE_BOLD + "=== CATÁLOGO ===" + CYAN + "       ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ " + YELLOW + "(1)" + RESET + " Adicionar Novo Item    " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(2)" + RESET + " Atualizar Item         " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(3)" + RESET + " Ver Catálogo           " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(0)" + RESET + " Voltar                 " + CYAN + "║");
            System.out.println("╚════════════════════════════╝" + RESET);
            System.out.print(GREEN + "Escolha uma opção: " + RESET);

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0 -> { return; }
                case 1 -> Trabalho.Operacoes.cadastrarItem();
                case 2 -> Trabalho.Operacoes.atualizarItem();
                case 3 -> Trabalho.Operacoes.verCatalogo();
                default -> System.out.println(RED + "Opção inválida." + RESET);
            }
        }
    }

    public static void menuEstoque() {
        while (true) {
            System.out.println(CYAN + "\n╔════════════════════════════╗");
            System.out.println("║      " + WHITE_BOLD + "=== ESTOQUE ===" + CYAN + "       ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ " + YELLOW + "(1)" + RESET + " Atualizar Estoque      " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(2)" + RESET + " Ver Estoque            " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(3)" + RESET + " Deletar Item           " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(0)" + RESET + " Voltar                 " + CYAN + "║");
            System.out.println("╚════════════════════════════╝" + RESET);
            System.out.print(GREEN + "Escolha uma opção: " + RESET);

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0 -> { return; }
                case 1 -> Trabalho.Operacoes.atualizarItem();
                case 2 -> Trabalho.Operacoes.verCatalogo();
                case 3 -> Trabalho.Operacoes.deletarItem();
                default -> System.out.println(RED + "Opção inválida." + RESET);
            }
        }
    }


    public static void menuReceitas() {
        while (true) {
            System.out.println(CYAN + "\n╔════════════════════════════╗");
            System.out.println("║     " + WHITE_BOLD + "=== RECEITAS ===" + CYAN +    "       ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ " + YELLOW + "(1)" + RESET + " Cadastrar Nova Receita " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(2)" + RESET + " Listar Receitas        " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(0)" + RESET + " Voltar                 " + CYAN + "║");
            System.out.println("╚════════════════════════════╝" + RESET);
            System.out.print(GREEN + "Escolha uma opção: " + RESET);

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0 -> { return; }
                case 1 -> Trabalho.Operacoes.cadastrarReceita();
                case 2 -> Trabalho.Operacoes.listarReceitas();
                default -> System.out.println(RED + "Opção inválida." + RESET);
            }
        }
    }

    public static void menuOperacoes() {
        while (true) {
            System.out.println(CYAN + "\n╔═════════════════════════════════╗");
            System.out.println("║ " + WHITE_BOLD + "=== OPERAÇÕES DE ENGENHARIA ===" + CYAN + " ║");
            System.out.println("╠═════════════════════════════════╣");
            System.out.println("║ " + YELLOW + "(1)" + RESET + " Construir Item              " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(2)" + RESET + " Dar Baixa em Item           " + CYAN + "║");
            System.out.println("║ " + YELLOW + "(0)" + RESET + " Voltar                      " + CYAN + "║");
            System.out.println("╚═════════════════════════════════╝" + RESET);
            System.out.print(GREEN + "Escolha uma opção: " + RESET);

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0 -> { return; }
                case 1 -> Trabalho.Operacoes.construirItem();
                case 2 -> Trabalho.Operacoes.darBaixaItem();
                default -> System.out.println(RED + "Opção inválida." + RESET);
            }
        }
    }
}

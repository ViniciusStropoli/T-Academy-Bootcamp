package Trabalho;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Operacoes {
    static Scanner sc = App.sc;
    static HashMap<String, Item> catalogo = App.catalogo;
    static ArrayList<Receita> receitas = App.receitas;

    public static void cadastrarItem() {
        System.out.println("Nome do item:");
        String nome = sc.nextLine();

        System.out.println("Quantidade em estoque:");
        double quantidade = sc.nextDouble();
        sc.nextLine();

        catalogo.put(nome, new Item(nome, quantidade));
        System.out.println("Item adicionado ao catálogo❗");
    }

    public static void atualizarItem() {
        System.out.println("Digite o nome do item para atualizar:");
        String nome = sc.nextLine();

        if (!catalogo.containsKey(nome)) {
            System.out.println("Item não encontrado!");
            return;
        }

        Item item = catalogo.get(nome);

        System.out.println("(1) - Atualizar Nome");
        System.out.println("(2) - Atualizar Quantidade");
        System.out.println("(3) - Excluir item do catálogo");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> {
                System.out.println("Novo nome:");
                String novoNome = sc.nextLine();
                catalogo.remove(nome);
                item.nome = novoNome;
                catalogo.put(novoNome, item);
                System.out.println("Nome atualizado.");
            }
            case 2 -> {
                System.out.println("Nova quantidade:");
                double novaQtd = sc.nextDouble();
                item.quantidade = novaQtd;
                System.out.println("Quantidade atualizada❗");
            }
            default -> System.out.println("❌Opção inválida❌");
        }
    }

    public static void verCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("❌Nenhum item no catálogo❌");
            return;
        }

        System.out.println("--- ❕CATÁLOGO DE ITENS❕ ---");
        for (Item item : catalogo.values()) {
            System.out.println("Item: " + item.nome);
            System.out.println("Quantidade: " + item.quantidade);
            System.out.println("--------------------------");
        }
    }

    public static void deletarItem() {
        System.out.print("Digite o nome do item a ser deletado: ");
        String nome = App.sc.nextLine();

        if (!App.catalogo.containsKey(nome)) {
            System.out.println("Item não encontrado no catálogo.");
            return;
        }

        App.catalogo.remove(nome);
        System.out.println("Item '" + nome + "' removido com sucesso!");
    }


    public static void cadastrarReceita() {
        System.out.println("Digite o nome do item final da receita:");
        String nomeFinal = sc.nextLine();

        Receita nova = new Receita(nomeFinal);

        while (true) {
            System.out.println("Digite o nome do ingrediente (ou 'fim' para encerrar):");
            String ing = sc.nextLine();
            if (ing.equalsIgnoreCase("fim")) break;

            System.out.println("Digite a quantidade de '" + ing + "':");
            double qtd = sc.nextDouble();
            sc.nextLine();

            nova.adicionarIngrediente(ing, qtd);
        }

        receitas.add(nova);
        System.out.println("Receita cadastrada com sucesso❗");
    }

    public static void listarReceitas() {
        if (receitas.isEmpty()) {
            System.out.println("❌Nenhuma receita cadastrada❌");
            return;
        }

        System.out.println("--- ❕RECEITAS CADASTRADAS❕ ---");
        for (Receita r : receitas) {
            System.out.println("Item final: " + r.nomeDoItemFinal);
            System.out.println("Ingredientes:");
            for (String ing : r.ingredientes.keySet()) {
                System.out.println(" - " + ing + ": " + r.ingredientes.get(ing));
            }
            System.out.println("--------------------------");
        }
    }

    public static void construirItem() {
        System.out.println("Digite o nome do item a ser construído:");
        String nome = sc.nextLine();

        Receita receita = null;
        for (Receita r : receitas) {
            if (r.nomeDoItemFinal.equalsIgnoreCase(nome)) {
                receita = r;
                break;
            }
        }

        if (receita == null) {
            System.out.println("Receita não encontrada.");
            return;
        }

        for (String ing : receita.ingredientes.keySet()) {
            if (!catalogo.containsKey(ing) || catalogo.get(ing).quantidade < receita.ingredientes.get(ing)) {
                System.out.println("Estoque insuficiente de '" + ing + "'");
                return;
            }
        }

        for (String ing : receita.ingredientes.keySet()) {
            catalogo.get(ing).quantidade -= receita.ingredientes.get(ing);
        }

        if (!catalogo.containsKey(nome)) {
            catalogo.put(nome, new Item(nome, 1));
        } else {
            catalogo.get(nome).quantidade += 1;
        }

        System.out.println("Item '" + nome + "' construído com sucesso!");
        AsciiArt.exibir(nome);
        System.out.println("Quantidade de '"+ nome +"' em estoque: "+ catalogo.get(nome).quantidade);
    }

    public static void darBaixaItem() {
        System.out.println("Digite o nome do item vendido:");
        String nome = sc.nextLine();

        if (!catalogo.containsKey(nome)) {
            System.out.println("Item não encontrado.");
            return;
        }

        Item item = catalogo.get(nome);
        if (item.quantidade <= 0) {
            System.out.println("Estoque zerado.");
        } else {
            item.quantidade -= 1;
            System.out.println("Item '" + nome + "' deu baixa (vendido).");
        }
    }
}
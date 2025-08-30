package Trabalho;

import java.util.*;

public class App {
    public static HashMap<String, Item> catalogo = new HashMap<>();
    public static ArrayList<Receita> receitas = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BancoDados.carregarReceitas();
        Menu.menuPrincipal();
    }
}

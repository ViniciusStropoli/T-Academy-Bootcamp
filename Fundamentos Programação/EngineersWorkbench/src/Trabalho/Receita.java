package Trabalho;

import java.util.HashMap;

public class Receita {
    public String nomeDoItemFinal;
    public HashMap<String, Double> ingredientes;

    public Receita(String nomeDoItemFinal) {
        this.nomeDoItemFinal = nomeDoItemFinal;
        this.ingredientes = new HashMap<>();
    }

    public void adicionarIngrediente(String nomeIngrediente, double quantidade) {
        ingredientes.put(nomeIngrediente, quantidade);
    }

    public String getNomeDoItemFinal() {
        return nomeDoItemFinal;
    }
}

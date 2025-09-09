package Controller;

import Model.Garrafa;

import java.util.ArrayList;
import java.util.List;

public class GarrafaController {
    private List<Garrafa> garrafas = new ArrayList<>();

    public void adicionarGarrafa(String nome, String cor, double capacidade) {
        Garrafa nova = new Garrafa(nome, cor, capacidade);
        garrafas.add(nova);
        System.out.println("Garrafa adicionada com sucesso: " + nova);
    }

    public List<Garrafa> buscarPorNome(String nomeBuscado) {
        List<Garrafa> resultado = new ArrayList<>();
        for (Garrafa g : garrafas) {
            if (g.getNome().toLowerCase().contains(nomeBuscado.toLowerCase())) {
                resultado.add(g);
            }
        }
        return resultado;
    }

    public Garrafa buscarPorId(int id) {
        for (Garrafa g : garrafas) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public List<Garrafa> listarTodas() {
        return garrafas;
    }
}

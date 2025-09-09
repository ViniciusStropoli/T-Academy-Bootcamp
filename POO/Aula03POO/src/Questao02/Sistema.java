package Questao02;

import java.time.LocalTime;
import java.util.ArrayList;

public class Sistema {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Transporte> transportes = new ArrayList<>();
    private ArrayList<Passagem> passagensVendidas = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarTransporte(Transporte transporte) {
        transportes.add(transporte);
    }

    public void venderPassagem(Usuario usuario, Passagem passagem) {
        if (usuario.getSaldo() >= passagem.getPreco()) {
            usuario.comprarPassagem(passagem.getPreco());
            passagensVendidas.add(passagem);
            System.out.println("Passagem para " + passagem.getDestino() + " vendida com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para comprar a passagem.");
        }
    }

    public void calcularRota(String origem, String destino) {
        System.out.println("Calculando rota de " + origem + " até " + destino + "...");
        for (Transporte t : transportes) {
            System.out.println("- Pegar " + t.modal + " modelo " + t.modelo);
        }
    }

    public void verificarAtrasos(LocalTime horarioAtual) {
        for (Transporte t : transportes) {
            if (t.verificarAtraso(horarioAtual)) {
                System.out.println("Atenção: " + t.modal + " modelo " + t.modelo + " está atrasado.");
            }
        }
    }

    public void gerarRelatorioPassagens() {
        System.out.println("\n=== Relatório de Passagens Vendidas ===");
        for (Passagem p : passagensVendidas) {
            System.out.println("Para " + p.getDestino() + " via " + p.getModal().modal + " - R$" + p.getPreco());
        }
        System.out.println("Total de passagens: " + passagensVendidas.size());
    }
}

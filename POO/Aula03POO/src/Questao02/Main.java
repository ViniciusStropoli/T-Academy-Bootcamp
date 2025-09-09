package Questao02;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // Criar usuários
        Usuario joao = new Usuario("João", 15.0);
        Usuario ana = new Usuario("Ana", 3.0);
        sistema.adicionarUsuario(joao);
        sistema.adicionarUsuario(ana);

        // Criar transportes
        Transporte onibus1 = new Transporte("Mercedes", Transporte.Modais.ONIBUS);
        onibus1.horarioPartida = LocalTime.of(8, 0);
        onibus1.horarioChegada = LocalTime.of(8, 45);

        Transporte metro1 = new Transporte("Linha Azul", Transporte.Modais.METRO);
        metro1.horarioPartida = LocalTime.of(8, 30);
        metro1.horarioChegada = LocalTime.of(9, 0);

        sistema.adicionarTransporte(onibus1);
        sistema.adicionarTransporte(metro1);

        // Criar passagens
        Passagem passagem1 = new Passagem(onibus1, "Centro", 4.50, "08:45", "08:00");
        Passagem passagem2 = new Passagem(metro1, "Zona Sul", 3.50, "09:00", "08:30");

        // Vender passagens
        sistema.venderPassagem(joao, passagem1);
        sistema.venderPassagem(ana, passagem2);

        // Calcular rota
        sistema.calcularRota("Bairro A", "Centro");

        // Verificar atrasos simulando que agora são 08:10
        sistema.verificarAtrasos(LocalTime.of(8, 10));

        // Gerar relatório
        sistema.gerarRelatorioPassagens();
    }
}

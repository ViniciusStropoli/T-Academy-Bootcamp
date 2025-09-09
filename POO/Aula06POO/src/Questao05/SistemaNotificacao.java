package Questao05;

public class SistemaNotificacao {
    public static void main(String[] args) {
        GerenciadorDeNotificacoes gerenciador = new GerenciadorDeNotificacoes();

        gerenciador.registrarNotificador(new EmailNotificador());
        gerenciador.registrarNotificador(new SMSNotificador());
        gerenciador.registrarNotificador(new PushNotificador());

        gerenciador.enviarParaTodos("Sistema será reiniciado às 22h.");
    }
}
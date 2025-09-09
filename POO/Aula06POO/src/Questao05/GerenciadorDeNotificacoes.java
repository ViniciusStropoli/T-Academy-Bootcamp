package Questao05;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeNotificacoes {
    private List<Notificador> notificadores = new ArrayList<>();

    public void registrarNotificador(Notificador notificador) {
        notificadores.add(notificador);
    }

    public void enviarParaTodos(String mensagem) {
        for (Notificador notificador : notificadores) {
            notificador.enviar(mensagem);
        }
    }
}

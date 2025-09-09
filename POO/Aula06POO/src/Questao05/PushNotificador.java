package Questao05;

public class PushNotificador implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando notificação push com a mensagem: " + mensagem);
    }
}

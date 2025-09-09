package Questao05;

public class SMSNotificador implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS com a mensagem: " + mensagem);
    }
}

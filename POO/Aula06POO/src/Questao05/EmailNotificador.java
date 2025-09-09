package Questao05;


public class EmailNotificador implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando email com a mensagem: " + mensagem);
    }
}

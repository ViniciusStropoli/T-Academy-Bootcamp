package Questao04;

public class Pix implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$ " + valor + " via PIX.");

        System.out.println("Gerando chave PIX...");

        if (valor <= 0) {
            System.out.println("Valor inválido para pagamento.");
            return;
        }

        System.out.println("Aguardando confirmação do banco...");

        boolean confirmado = Math.random() > 0.1;

        if (confirmado) {
            System.out.println("Pagamento instantâneo de R$ " + valor + " realizado via PIX.");
        } else {
            System.out.println("Erro na transação PIX. Tente novamente.");
        }
    }
}

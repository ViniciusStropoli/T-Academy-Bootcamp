package Questao04;

public class Paypal implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$ " + valor + " via PayPal.");

        System.out.println("Conectando ao PayPal...");

        if (valor <= 0) {
            System.out.println("Valor inválido para pagamento.");
            return;
        }

        System.out.println("Autenticando usuário no PayPal...");
        boolean autenticado = true;

        if (autenticado) {
            System.out.println("Pagamento de R$ " + valor + " realizado com sucesso via PayPal.");
        } else {
            System.out.println("Falha na autenticação do PayPal.");
        }

    }
}

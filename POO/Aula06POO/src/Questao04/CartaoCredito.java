package Questao04;

public class CartaoCredito implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$ " + valor + " via Cartão de Crédito.");

        System.out.println("Iniciando processamento via Cartão de Crédito...");

        if (valor <= 0) {
            System.out.println("Valor inválido para pagamento.");
            return;
        }

        System.out.println("Verificando limite do cartão...");
        boolean limiteDisponivel = valor <= 1000;

        if (limiteDisponivel) {
            System.out.println("Pagamento aprovado no Cartão de Crédito!");
        } else {
            System.out.println("Pagamento recusado: limite insuficiente.");
        }
    }
}

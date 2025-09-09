package Questao04;

public class SistemaPagamento {
    public static void main(String[] args) {
        MetodoPagamento pagamento;

        System.out.println("\n--- PAGAMENTO 1 ---");
        pagamento = new CartaoCredito();
        pagamento.processarPagamento(850.00);

        System.out.println("\n--- PAGAMENTO 2 ---");
        pagamento = new Paypal();
        pagamento.processarPagamento(250.00);

        System.out.println("\n--- PAGAMENTO 3 ---");
        pagamento = new Pix();
        pagamento.processarPagamento(100.00);
    }
}

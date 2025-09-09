package Lista.Questao02;

public class Main {
    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente("Benício", 100, 1000);
        ContaPoupanca contaPoupanca = new ContaPoupanca("Ana", 100, 50);

        contaCorrente.sacar(50);
        contaPoupanca.depositar(50);

        contaCorrente.getSaldo();
        contaPoupanca.getSaldo();
    }
}

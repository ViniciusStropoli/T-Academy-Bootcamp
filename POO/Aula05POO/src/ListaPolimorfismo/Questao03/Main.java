package ListaPolimorfismo.Questao03;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria();
        conta1.setTitular("Maria");
        conta1.setNumero(123);
        conta1.setSaldo(1000);
        conta1.depositar(500);
        conta1.sacar(200, 5);

        System.out.println();

        ContaCorrente conta2 = new ContaCorrente(10);
        conta2.setTitular("João");
        conta2.setNumero(456);
        conta2.setSaldo(1000);
        conta2.depositar(500);

        System.out.println("Titular: " + conta2.getTitular());
        System.out.println("Saldo final: " + conta2.getSaldo());
    }
}

package Lista.Questao02;

public class ContaBancaria {
    private String titular;
    private double saldo;

    public void depositar(double deposito) {
        deposito = 0;
        this.saldo = saldo + deposito;
    }

    public String sacar(double saque) {
        saque = 0;
        this.saldo = saldo - saque;
        return "Saque realizado com sucesso!";
    }

    public String consultarSaldo() {
        return "Seu saldo é: " + saldo;
    }

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

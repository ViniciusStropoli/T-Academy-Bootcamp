package ListaPolimorfismo.Questao03;

public class ContaBancaria {
    private String titular;
    private int numero;
    private double saldo;

    public void depositar(double deposito) {
        this.saldo += deposito;
        System.out.println("Depositado com sucesso!");
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void sacar(double saque) {
        this.saldo -= saque;
        System.out.println("Sacado com sucesso!");
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void depositar(double deposito, String descricao) {
        this.saldo += deposito;
        System.out.println("Saldo atual: " + this.saldo);
        System.out.println("Descricao: " + descricao);
    }

    public void sacar(double saque, double taxa) {
        double valorComTaxa = saque + (saque * (taxa / 100));
        this.saldo -= valorComTaxa;
    }


    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

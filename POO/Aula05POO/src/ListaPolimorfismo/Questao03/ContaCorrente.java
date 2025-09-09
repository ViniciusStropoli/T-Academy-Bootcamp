package ListaPolimorfismo.Questao03;

public class ContaCorrente extends ContaBancaria{
    private double taxaServicos;

    public ContaCorrente(double taxaServicos) {
        this.taxaServicos = taxaServicos;
    }

    @Override
    public void depositar(double deposito) {
        double valorComTaxa = deposito - (deposito * (taxaServicos / 100));
        setSaldo(getSaldo() + valorComTaxa);
        System.out.println("Depositado com desconto de taxa.");
        System.out.println("Saldo atual: " + getSaldo());
    }
}

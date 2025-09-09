package Lista.Questao04;

public class Vendedor extends Funcionario {
    private int totalVendas;

    public Vendedor(String nome, int cargaHoraria, double salarioHora, int totalVendas) {
        super(nome, cargaHoraria, salarioHora);
        this.totalVendas = totalVendas;
    }

    @Override
    public double calcularSalarioMensal() {
        double salarioBase = super.calcularSalarioMensal();
        return salarioBase + (totalVendas * 10);
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }
}


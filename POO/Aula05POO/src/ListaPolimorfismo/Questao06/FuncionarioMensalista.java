package ListaPolimorfismo.Questao06;

public class FuncionarioMensalista extends Funcionario {
    private double salarioFixo;
    private boolean desempenho;

    public FuncionarioMensalista(String nome, double bonus, double salarioFixo, boolean desempenho) {
        super(nome, bonus);
        this.salarioFixo = salarioFixo;
        this.desempenho = desempenho;
    }

    @Override
    public double calcularSalario() {
        if (desempenho) {
            return salarioFixo + getBonus();
        } else {
            return salarioFixo;
        }
    }

    @Override
    public void exibirHolerite() {
        double total = calcularSalario();

        System.out.println("Holerite de " + getNome() + " (Mensalista)");
        System.out.println("Salário base: R$" + salarioFixo);
        if (desempenho) {
            System.out.println("Desempenho: Bom ✅");
            System.out.println("Bônus aplicado: R$" + getBonus());
        } else {
            System.out.println("Desempenho: Insuficiente ❌");
            System.out.println("Bônus aplicado: R$0.00");
        }
        System.out.println("Salário final: R$" + total);
        System.out.println("-----------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " (Mensalista)";
    }

    public double getSalarioFixo() {
        return salarioFixo;
    }

    public void setSalarioFixo(double salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    public boolean isDesempenho() {
        return desempenho;
    }

    public void setDesempenho(boolean desempenho) {
        this.desempenho = desempenho;
    }
}

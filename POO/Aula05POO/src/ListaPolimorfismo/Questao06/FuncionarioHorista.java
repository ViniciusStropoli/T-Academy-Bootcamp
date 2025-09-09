package ListaPolimorfismo.Questao06;

public class FuncionarioHorista extends Funcionario {
    private double salarioHora;
    private int horaTrabalhada;
    private int horaExtra;

    public FuncionarioHorista(String nome, double bonus, double salarioHora, int horaTrabalhada, int horaExtra) {
        super(nome, bonus);
        this.salarioHora = salarioHora;
        this.horaTrabalhada = horaTrabalhada;
        this.horaExtra = horaExtra;
    }

    @Override
    public double calcularSalario() {
        double salarioBase = salarioHora * horaTrabalhada;
        double salarioExtras = salarioHora * 1.5 * horaExtra;
        return salarioBase + salarioExtras;
    }

    @Override
    public void exibirHolerite() {
        double salarioBase = salarioHora * horaTrabalhada;
        double salarioExtras = salarioHora * 1.5 * horaExtra;
        double total = calcularSalario();

        System.out.println("Holerite de " + getNome() + " (Horista)");
        System.out.println("Horas normais: " + horaTrabalhada);
        System.out.println("Horas extras: " + horaExtra);
        System.out.println("Valor hora: R$" + salarioHora);
        System.out.println("Salário base: R$" + salarioBase);
        System.out.println("Adicional de horas extras: R$" + salarioExtras);
        System.out.println("Salário final: R$" + total);
        System.out.println("-----------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " (Horista)";
    }
}


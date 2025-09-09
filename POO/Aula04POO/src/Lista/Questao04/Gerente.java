package Lista.Questao04;

public class Gerente extends Funcionario {
    private double bonus;

    public Gerente(String nome, int cargaHoraria, double salarioHora, double bonus) {
        super(nome, cargaHoraria, salarioHora);
        this.bonus = bonus;
    }

    @Override
    public double calcularSalarioMensal() {
        double salarioBase = super.calcularSalarioMensal();
        return salarioBase + bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}

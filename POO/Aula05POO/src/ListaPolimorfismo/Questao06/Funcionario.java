package ListaPolimorfismo.Questao06;

public abstract class Funcionario {
    private String nome;
    private double bonus;

    public Funcionario(String nome, double bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    public abstract double calcularSalario();

    public abstract void exibirHolerite();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Funcionário: " + nome;
    }
}

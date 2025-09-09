package Lista.Questao04;

public class Funcionario {
    private String nome;
    private int cargaHoraria;
    private double salarioHora;

    public Funcionario(String nome, int cargaHoraria, double salarioHora) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.salarioHora = salarioHora;
    }

    public double calcularSalarioMensal() {
        return salarioHora * cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }
}

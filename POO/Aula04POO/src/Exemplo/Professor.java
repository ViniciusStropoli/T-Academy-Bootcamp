package Exemplo;

public class Professor extends Pessoa{
    private String graduacao;
    private double salario;

    public Professor(String graduacao, double salario) {
        this.graduacao = graduacao;
        this.salario = salario;
    }

        public Professor(int id, String nome, int cpf, String graduacao, double salario) {
        super(id, nome, cpf);
        this.graduacao = graduacao;
        this.salario = salario;
    }

    public String getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(String graduacao) {
        this.graduacao = graduacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}

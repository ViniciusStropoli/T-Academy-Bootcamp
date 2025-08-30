package sistemaConsulta;

public class Paciente {
    private String nome;
    private double peso;
    private double altura;
    private Consulta consultaAgendada;

    public Paciente(String nome, double peso, double altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularImc() {
        return peso / (altura * altura);
    }

    public String getNome() {
        return nome;
    }

    public void agendarConsulta(Consulta consulta) {
        this.consultaAgendada = consulta;
    }

    public void exibirDados() {
        System.out.println("\n--- Dados do Paciente ---");
        System.out.println("Nome: " + nome);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
        System.out.printf("IMC: %.2f\n", calcularImc());
        if (consultaAgendada != null) {
            consultaAgendada.exibirConsulta();
        } else {
            System.out.println("Nenhuma consulta agendada.");
        }
    }
}
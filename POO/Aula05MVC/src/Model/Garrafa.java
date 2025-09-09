package Model;

public class Garrafa {
    private int id;
    private String nome;
    private String cor;
    private double capacidade;
    private static int contador;

    public Garrafa(String nome, String cor, double capacidade) {
        contador++;
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {this.cor = cor;}

    public void setCapacidade(double capacidade) {this.capacidade = capacidade;}

    @Override
    public String toString() {
        return "Garrafa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }
}

package ListaPolimorfismo.Questao02;

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public double getPreco() {return preco;}

    public void setPreco(double preco) {this.preco = preco;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}


    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
        System.out.println("Estoque adicionado com sucesso, quantidade atual: " + this.quantidade);
    }

    public void adicionarEstoque(int quantidade, double descontos) {
        this.quantidade += quantidade;
        this.preco = preco - descontos;
        System.out.println("Estoque adicionado com sucesso, quantidade atual: " + this.quantidade + ", descontos: " + descontos);
    }
}

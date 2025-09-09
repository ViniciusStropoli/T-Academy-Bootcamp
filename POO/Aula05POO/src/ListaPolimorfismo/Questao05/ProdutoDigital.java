package ListaPolimorfismo.Questao05;

public class ProdutoDigital extends Item {

    public ProdutoDigital(String nome, int quantidade, double preco) {
        super(nome, quantidade, preco);
    }

    @Override
    public double calcularPreco() {
        return getPreco() * getQuantidade();
    }
}

package ListaPolimorfismo.Questao05;

public class ProdutoFisico extends Item {
    private double frete;

    public ProdutoFisico(String nome, int quantidade, double preco, double frete) {
        super(nome, quantidade, preco);
        this.frete = frete;
    }

    @Override
    public double calcularPreco() {
        return (getPreco() * getQuantidade()) + frete;
    }

    public double getFrete() { return frete; }
    public void setFrete(double frete) { this.frete = frete; }
}

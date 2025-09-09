package Questao02;

public class Usuario {
    private String nome;
    private double saldo;

    public Usuario(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public void comprarPassagem(double preco) {
        if(saldo > preco){
            saldo -= preco;
            System.out.println(nome +" comprou uma passagem de: R$ "+ preco);
        } else System.out.println(nome + " não possuí saldo o suficiente!");
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

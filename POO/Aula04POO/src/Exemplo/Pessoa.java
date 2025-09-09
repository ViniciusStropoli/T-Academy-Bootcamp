package Exemplo;

public class Pessoa {
    private int id;
    private String nome;
    private int cpf;

    public Pessoa() {}
    public Pessoa(int id, String nome, int cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



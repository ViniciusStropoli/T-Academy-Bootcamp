package Exemplos;

public abstract class Personagem {
    private int id;
    private String nome;
    private int vida;
    private int defesa;
    public static int contador;

    public Personagem(String nome, int vida, int defesa) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.vida = vida;
        this.defesa = defesa;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getVida() {return vida;}

    public void setVida(int vida) {this.vida = vida;}

    public int getDefesa() {return defesa;}

    public void setDefesa(int defesa) {this.defesa = defesa;}

    public abstract void atacar(Personagem personagem);

}

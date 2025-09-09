package Exemplo01;

public abstract class Animal {
    protected int id;
    protected String especie;
    protected String cor;
    protected int contador;

    public Animal( String especie, String cor) {
        contador++;
        this.id = contador;
        this.especie = especie;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", especie='" + especie + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}

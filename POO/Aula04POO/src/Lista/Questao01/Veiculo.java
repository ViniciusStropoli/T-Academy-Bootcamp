package Lista.Questao01;

public class Veiculo {
    private String marca;
    private int ano;

    public Veiculo(String marca, int ano){
        this.marca = marca;
        this.ano = ano;
    }

    public String mostrarDetalhes() {
        String detalhes = "Marca: "+marca+" Ano: "+ano;
        return detalhes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}

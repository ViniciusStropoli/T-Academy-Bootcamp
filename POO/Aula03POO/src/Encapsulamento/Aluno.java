/*package Encapsulamento;

import java.util.ArrayList;

public class Aluno {
    private String nome;
    private String matricula;
    private ArrayList<Double> notas;
    public Aluno(){
        this.notas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void addNota(double nota) {
        this.notas.add(nota);
    }
    public double getMedia(){
        double soma = 0;
        for (Double nota : notas) {
            soma += nota;
        }
        return soma / this.notas.size();
    }
    public String getSituacao(){
        return getMedia() > 7 ? "Aprovado" : "Reprovado";
    }
}
*/

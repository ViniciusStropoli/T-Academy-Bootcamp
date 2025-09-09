package Questao03;

public class LeitorCSV implements ArquivoLeitor {
    @Override
    public void ler(String caminho) {
        if (caminho.equals("C://Arquivo.csv")) {
            System.out.println("O arquivo tem um formato compatível (.csv), iniciando leitura:");
            System.out.println("Conteúdo fictício do CSV...");
        } else {
            System.out.println("Este arquivo não possui um formato compatível com .csv, tente com outro leitor.");
        }
    }
}

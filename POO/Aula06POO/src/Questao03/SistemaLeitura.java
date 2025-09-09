package Questao03;

public class SistemaLeitura {
    public static void main(String[] args) {
        ArquivoLeitor leitor;

        leitor = new LeitorTXT();
        leitor.ler("C://Arquivo.txt");

        System.out.println();

        leitor = new LeitorCSV();
        leitor.ler("C://Arquivo.csv");

        System.out.println();

        leitor = new LeitorJSON();
        leitor.ler("C://Arquivo.json");

        System.out.println();

        leitor = new LeitorTXT();
        leitor.ler("C://OutroArquivo.csv");
    }
}

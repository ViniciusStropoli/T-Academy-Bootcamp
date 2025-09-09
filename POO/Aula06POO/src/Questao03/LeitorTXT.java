package Questao03;

public class LeitorTXT implements ArquivoLeitor {
    @Override
    public void ler(String caminho) {
        if (caminho.equals("C://Arquivo.txt")) {
            System.out.println("O arquivo tem um formato compatível (.txt), iniciando leitura:");
            System.out.println("Conteúdo do TXT...");
        } else {
            System.out.println("Este arquivo não possui um formato compatível com .txt, tente com outro leitor.");
        }
    }
}

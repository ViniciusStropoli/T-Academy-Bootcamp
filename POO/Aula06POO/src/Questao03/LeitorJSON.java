package Questao03;

public class LeitorJSON implements ArquivoLeitor {
    @Override
    public void ler(String caminho) {
        if (caminho.equals("C://Arquivo.json")) {
            System.out.println("O arquivo tem um formato compatível (.json), iniciando leitura:");
            System.out.println("Conteúdo fictício do JSON...");
        } else {
            System.out.println("Este arquivo não possui um formato compatível com .json, tente com outro leitor.");
        }
    }
}

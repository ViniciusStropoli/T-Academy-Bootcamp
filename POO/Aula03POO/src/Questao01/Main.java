package Questao01;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Conteudo filme1 = new Conteudo("Matrix", "2h15min", Conteudo.Categoria.FILME);
        Conteudo filme2 = new Conteudo("Interestelar", "2h50min", Conteudo.Categoria.FILME);
        Conteudo serie1 = new Conteudo("Breaking Bad", "5 temporadas", Conteudo.Categoria.SERIE);
        Conteudo doc1 = new Conteudo("Planeta Terra", "1h30min", Conteudo.Categoria.DOCUMENTARIO);
        Conteudo musica1 = new Conteudo("Bohemian Rhapsody", "6min", Conteudo.Categoria.MUSICA);

        sistema.adicionarConteudo(filme1);
        sistema.adicionarConteudo(filme2);
        sistema.adicionarConteudo(serie1);
        sistema.adicionarConteudo(doc1);
        sistema.adicionarConteudo(musica1);

        Usuario usuario1 = new Usuario("João", "joao@email.com", "1234", Usuario.Plano.GRATUITO);
        Usuario usuario2 = new Usuario("Maria", "maria@email.com", "abcd", Usuario.Plano.PREMIUM);

        sistema.adicionarUsuario(usuario1);
        sistema.adicionarUsuario(usuario2);

        usuario1.assistirConteudo(filme1, 135);
        usuario1.assistirConteudo(doc1, 90);
        usuario1.avaliarConteudo(filme1, 4.5, "Muito bom!");
        usuario1.adicionarAPlaylist(filme1);
        usuario1.adicionarAPlaylist(doc1);

        usuario2.assistirConteudo(filme2, 170);
        usuario2.assistirConteudo(serie1, 300);  // tempo assistido fictício
        usuario2.avaliarConteudo(filme2, 5.0, "Incrível!");
        usuario2.avaliarConteudo(serie1, 4.8, "Viciante!");

        System.out.println("\nRecomendações para João:");
        ArrayList<Conteudo> recomendacoesJoao = sistema.recomendarParaUsuario(usuario1);
        for (Conteudo conteudo : recomendacoesJoao) {
            System.out.println("- " + conteudo.getNome() + " (" + conteudo.getCategoria() + ") - Estrelas: " + conteudo.getEstrelas());
        }

        System.out.println("\n=== Estatísticas ===");
        sistema.exibirEstatisticasGerais();

        System.out.println("\nJoão assistiu um total de: " + usuario1.getTempoTotalAssistido() + " minutos.");
        System.out.println("Maria assistiu um total de: " + usuario2.getTempoTotalAssistido() + " minutos.");
    }
}

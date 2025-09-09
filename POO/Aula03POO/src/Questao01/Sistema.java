package Questao01;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Conteudo> catalogo = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarConteudo(Conteudo conteudo) {
        catalogo.add(conteudo);
    }

    public ArrayList<Conteudo> recomendarParaUsuario(Usuario usuario) {
        catalogo.sort((c1, c2) -> Integer.compare(c2.getCurtidas(), c1.getCurtidas()));
        return new ArrayList<>(catalogo.subList(0, Math.min(5, catalogo.size())));
    }

    public void exibirEstatisticasGerais() {
        System.out.println("Total de usuários: " + usuarios.size());
        System.out.println("Total de conteúdos no catálogo: " + catalogo.size());
    }
}

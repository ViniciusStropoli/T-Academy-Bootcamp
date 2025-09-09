package Exemplos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Personagem mago = new Mago("abc");
        Personagem guerreiro = new Guerreiro("bcd");

        mago.atacar(guerreiro);
        guerreiro.atacar(mago);

        ArrayList<Personagem> listaPersonagens = new ArrayList<>();
        listaPersonagens.add(mago);
        listaPersonagens.add(guerreiro);
    }
}

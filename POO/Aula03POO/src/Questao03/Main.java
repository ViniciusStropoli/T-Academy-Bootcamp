package Questao03;

public class Main {
    public static void main(String[] args) {
        // Criar professor
        Professor prof = new Professor("Prof. Clara", "clara@escola.com");

        // Criar missão
        Recompensa recompensa = new Recompensa(150, "Medalha de Lógica");
        Missao missaoLogica = prof.criarMissao("Missão dos Números", recompensa);

        // Adicionar desafios
        missaoLogica.adicionarDesafio(new Desafio(
                Desafio.TipoDesafio.LOGICA, "Quanto é 2 + 2 * 2?", "6"));

        missaoLogica.adicionarDesafio(new Desafio(
                Desafio.TipoDesafio.MULTIPLA_ESCOLHA, "Qual a capital do Brasil?", "Brasília"));

        // Criar aluno
        Aluno aluno1 = new Aluno("Lucas", "lucas@escola.com", "Sir Lucas");

        // Simular resposta correta
            for (Desafio d : missaoLogica.getDesafios()) {
            System.out.println("Desafio: " + d.getEnunciado());
            System.out.println("Resposta correta? " + d.verificarResposta(d.getRespostaCorreta()));
        }

        // Completar missão
        aluno1.completarMissao(missaoLogica);

        // Exibir personagem
        System.out.println("Personagem: " + aluno1.getPersonagem().getNome());
        System.out.println("Nível: " + aluno1.getPersonagem().getNivel());
        System.out.println("XP: " + aluno1.getPersonagem().getXp());
    }
}

package Questao03;

public class Desafio {
    public enum TipoDesafio {
        MULTIPLA_ESCOLHA, LOGICA, PROBLEMA
    }

    private TipoDesafio tipo;
    private String enunciado;
    private String respostaCorreta;

    public Desafio(TipoDesafio tipo, String enunciado, String respostaCorreta) {
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
    }

    public boolean verificarResposta(String resposta) {
        return resposta.equalsIgnoreCase(respostaCorreta);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public TipoDesafio getTipo() {
        return tipo;
    }
}

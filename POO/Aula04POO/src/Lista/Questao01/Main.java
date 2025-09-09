package Lista.Questao01;

public class Main {
    public static void main(String[] args) {
        Carro c1 = new Carro("Volkswagen", 2025, "Polo GTS", "Preto");
        Aviao a1 = new Aviao("Embraer", 2025, "Latam", "737");

        System.out.println(c1.mostrarDetalhes());
        System.out.println(a1.mostrarDetalhes());
    }
}

package Lista03;

public class Exe38 {
    public static void main(String[] args) {

        double salarioInicial = 1000.0;
        double salarioAtual = salarioInicial;
        double percentualAumento = 1.5 / 100;
        int anoAtual = 2025;

        for (int ano = 1996; ano <= anoAtual; ano++) {
            double aumento = salarioInicial * percentualAumento;
            salarioAtual += aumento;
            percentualAumento *= 2;
        }

        System.out.printf("Salário atual em %d: R$ %.2f%n", anoAtual, salarioAtual);
    }
}

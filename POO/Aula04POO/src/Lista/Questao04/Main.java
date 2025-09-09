package Lista.Questao04;

public class Main {
    public static void main(String[] args) {
        Gerente gerente = new Gerente("Jonas", 200, 2.50, 500);
        Vendedor vendedor = new Vendedor("Juan", 210, 2.00, 50);

        double salarioGerente = gerente.calcularSalarioMensal();
        double salarioVendedor = vendedor.calcularSalarioMensal();

        System.out.println("Salário do Gerente: R$ " + salarioGerente);
        System.out.println("Salário do Vendedor: R$ " + salarioVendedor);
    }
}
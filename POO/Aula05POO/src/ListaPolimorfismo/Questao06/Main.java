package ListaPolimorfismo.Questao06;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new FuncionarioHorista("João", 0.0, 25.0, 160, 10));
        funcionarios.add(new FuncionarioMensalista("Maria", 500.0, 3000.0, true));
        funcionarios.add(new FuncionarioMensalista("Carlos", 500.0, 2800.0, false));

        double totalSalarios = 0.0;
        double maiorSalario = 0.0;
        Funcionario melhorPago = null;

        System.out.println("RELATÓRIO DE PAGAMENTOS\n");

        for (Funcionario f : funcionarios) {
            f.exibirHolerite();
            double salario = f.calcularSalario();

            totalSalarios += salario;
            if (salario > maiorSalario) {
                maiorSalario = salario;
                melhorPago = f;
            }
        }

        double media = totalSalarios / funcionarios.size();

        System.out.println("\nRESUMO GERAL");
        System.out.printf("Total gasto com salários: R$%.2f%n", totalSalarios);
        System.out.printf("Média salarial: R$%.2f%n", media);
        System.out.printf("Maior salário: R$%.2f (%s)%n", maiorSalario, melhorPago.getNome());
    }
}

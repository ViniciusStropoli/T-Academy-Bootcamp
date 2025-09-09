package Lista.Questao02;

public class ContaCorrente extends ContaBancaria{
    private double limiteChequeEspecial;

    public ContaCorrente(String titular, double saldo, double limiteChequeEspecial) {
        super(titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public String sacar(double saque) {
        super.sacar(saque);
        if (limiteChequeEspecial <= saque) {
            return "Limite indisponivel";
        } else {
            return "Saque realizado com sucesso";
        }
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
}

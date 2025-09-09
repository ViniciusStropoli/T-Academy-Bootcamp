package Lista.Questao01;

public class Aviao extends Veiculo {
    private String companiaAerea;
    private String modelo;

    @Override
    public String mostrarDetalhes() {
        return super.mostrarDetalhes() +"Compania Aerea: "+companiaAerea+" Modelo: "+modelo;
    }

    public Aviao(String marca, int ano, String companiaAerea, String modelo) {
        super(marca, ano);
        this.companiaAerea = companiaAerea;
        this.modelo = modelo;
    }

    public String getCompaniaAerea() {
        return companiaAerea;
    }

    public void setCompaniaAerea(String companiaAerea) {
        this.companiaAerea = companiaAerea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}

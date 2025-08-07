package sistemaConsulta;

public class Consulta {
    private String data;
    private String hora;

    public Consulta(String data, String hora) {
        this.data = data;
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public void exibirConsulta() {
        System.out.println("Consulta agendada para: " + data + " às " + hora);
    }
}



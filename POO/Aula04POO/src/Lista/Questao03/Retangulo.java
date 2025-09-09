package Lista.Questao03;

public class Retangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Retangulo(int faces, int arestas, int base, int altura) {
        super(faces, arestas);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        super.calcularArea();
        return base * altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}

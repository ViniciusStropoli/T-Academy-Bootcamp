package Lista.Questao03;

public class Triangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(int faces, int arestas, double base, double altura) {
        super(faces, arestas);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        super.calcularArea();
        return (this.base*this.altura)/2;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}

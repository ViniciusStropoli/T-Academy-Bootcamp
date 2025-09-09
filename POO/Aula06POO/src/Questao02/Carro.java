    package Questao02;

    public class Carro extends Transporte implements Terrestre{
        private int id;
        private int contador;

        public Carro(String marca, String modelo, String cor, int id, int gasolina, int contador) {
            super(marca, modelo, cor, gasolina);
            this.id = id;
            this.gasolina = gasolina;
            this.contador = contador;
            this.gasolina = gasolina;
        }

        @Override
        public void dirigir() {
            if (gasolina > 0) {
                System.out.println("O carro possui " + this.gasolina + " litros de gasolina.");
                System.out.println("Você liga o seu " + this.modelo + " e dirige pelas ruas e rodovias terrestres!");
            } else System.out.println("O carro está sem gasolina!");
        }

        @Override
        public void abastecer() {
            this.gasolina = 200;
            System.out.println("Hidroavião abastecido com 200 litros.");
        }

        public int getId() {return id;}

        public void setId(int id) {this.id = id;}

        public String getMarca() {return marca;}

        public void setMarca(String marca) {this.marca = marca;}

        public String getModelo() {return modelo;}

        public void setModelo(String modelo) {this.modelo = modelo;}

        public String getCor() {return cor;}

        public void setCor(String cor) {this.cor = cor;}

        public int getContador() {return contador;}

        public void setContador(int contador) {this.contador = contador;}
    }

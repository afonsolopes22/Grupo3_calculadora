package pt.ulusofona.deisi.drsuiagen.calculator;

public class Memoria {

    private double valorMemoria;

    public void armazenar(double valor) {
        this.valorMemoria = valor;
    }

    public double obterValor() {
        return valorMemoria;
    }

    public void limpar() {
        this.valorMemoria = 0;
    }
}

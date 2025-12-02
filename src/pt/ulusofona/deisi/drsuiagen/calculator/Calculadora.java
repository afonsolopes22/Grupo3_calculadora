package pt.ulusofona.deisi.drsuiagen.calculator;

public class Calculadora {

    Visor visor;
    Memoria memoria;
    Bateria bateria;
    double valorAtual;

    public Calculadora(Visor visor, Memoria memoria, Bateria bateria) {
        this.visor = visor;
        this.memoria = memoria;
        this.bateria = bateria;
        this.valorAtual = 0;
    }

    public double adicionar(double a, double b) {
        gastarBateria();
        valorAtual = a + b;
        visor.exibir(String.valueOf(valorAtual));
        return valorAtual;
    }

    public double subtrair(double a, double b) {
        gastarBateria();
        valorAtual = a - b;
        visor.exibir(String.valueOf(valorAtual));
        return valorAtual;
    }

    public double multiplicar(double a, double b) {
        gastarBateria();
        valorAtual = a * b;
        visor.exibir(String.valueOf(valorAtual));
        return valorAtual;
    }

    public double dividir(double a, double b) {
        gastarBateria();
        if (b == 0) {
            visor.exibir("Erro: divisão por zero");
            return Double.NaN;
        }
        valorAtual = a / b;
        visor.exibir(String.valueOf(valorAtual));
        return valorAtual;
    }

    public void armazenarMemoria() {
        memoria.armazenar(valorAtual);
    }

    public double obterMemoria() {
        double valor = memoria.obterValor();
        visor.exibir(String.valueOf(valor));
        return valor;
    }

    public void limparMemoria() {
        memoria.limpar();
    }

    public double obterValorAtual() {
        return valorAtual;
    }

    public void limpar() {
        valorAtual = 0;
        visor.limpar();
    }

    public void verificarBateria() {
        double nivel = bateria.verificarNivel();
        visor.exibir("Bateria: " + nivel + "%");
    }

    private void gastarBateria() {
        bateria.gastar(1);  // gasta 1% por operação
    }
}

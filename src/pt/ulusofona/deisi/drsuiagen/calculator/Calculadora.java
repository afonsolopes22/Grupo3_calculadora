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

    private void gastarBateria() {
        bateria.gastar(5);
    }

    private void atualizarVisor() {
        visor.exibir(valorAtual + " | Bateria: " + bateria.verificarNivel() + "%");
    }

    public double adicionar(double a, double b) {
        gastarBateria();
        valorAtual = a + b;
        atualizarVisor();
        return valorAtual;
    }

    public double subtrair(double a, double b) {
        gastarBateria();
        valorAtual = a - b;
        atualizarVisor();
        return valorAtual;
    }

    public double multiplicar(double a, double b) {
        gastarBateria();
        valorAtual = a * b;
        atualizarVisor();
        return valorAtual;
    }

    public double dividir(double a, double b) {
        gastarBateria();
        if (b == 0) {
            visor.exibir("Erro: divisão por zero | Bateria: " + bateria.verificarNivel() + "%");
            return Double.NaN;
        }
        valorAtual = a / b;
        atualizarVisor();
        return valorAtual;
    }

    // Potência: a ^ b
    public double potencia(double base, double expoente) {
        gastarBateria();
        valorAtual = Math.pow(base, expoente);
        atualizarVisor();
        return valorAtual;
    }

    // Operação “genérica” para coisas fora das 4 operações (ex.: desenhar gráfico)
    public void gastarOperacaoExtra() {
        gastarBateria();
        visor.exibir("Operação realizada | Bateria: " + bateria.verificarNivel() + "%");
    }

    public double getNivelBateria() {
        return bateria.verificarNivel();
    }
}

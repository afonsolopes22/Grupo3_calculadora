package pt.ulusofona.deisi.drsuiagen.calculator;

public class Bateria {

    private double nivel;

    public Bateria(double nivelInicial) {
        this.nivel = Math.min(100, Math.max(0, nivelInicial));
    }

    public void gastar(double quantidade) {
        nivel = Math.max(0, nivel - quantidade);
    }

    public double verificarNivel() {
        return nivel;
    }
}


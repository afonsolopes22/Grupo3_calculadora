package pt.ulusofona.deisi.drsuiagen.calculator;

public class Bateria {

    double nivel;

    public Bateria(double nivelInicial) {
        this.nivel = nivelInicial;
    }

    public double verificarNivel() {
        return nivel;
    }

    public void gastar(double percentagem) {
        nivel -= percentagem;
        if (nivel < 0) {
            nivel = 0;
        }
    }
}

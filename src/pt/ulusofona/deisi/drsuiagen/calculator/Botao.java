package pt.ulusofona.deisi.drsuiagen.calculator;

public class Botao {

    String texto;
    String tipo;

    public Botao(String texto, String tipo) {
        this.texto = texto;
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public String getTipo() {
        return tipo;
    }

    public Object pressionar() {
        return 1;
    }
}

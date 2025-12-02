package pt.ulusofona.deisi.drsuiagen.calculator;

public class Visor {

     String valor;

    public void exibir(String valor) {
        this.valor = valor;
        System.out.println("" + valor);
    }

    public void limpar() {
        this.valor = "";
        System.out.println("");
    }

    public String getValor() {
        return valor;
    }
}

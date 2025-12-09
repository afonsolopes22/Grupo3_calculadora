package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.Visor;

import static org.junit.jupiter.api.Assertions.*;

public class VisorTest {

    @Test
    public void testExibirAtualizaValor() {
        Visor visor = new Visor();

        visor.exibir("123.45");

        assertEquals("123.45", visor.getValor());
    }

    @Test
    public void testLimpar() {
        Visor visor = new Visor();

        visor.exibir("abc");
        visor.limpar();

        assertEquals("", visor.getValor());
    }



}

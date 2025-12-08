package pt.ulusofona.deisi.drsuiagen.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemoriaTest {

    @Test
    public void testArmazenarEObter() {
        Memoria memoria = new Memoria();

        memoria.armazenar(42.5);
        double valor = memoria.obterValor();

        assertEquals(42.5, valor, 0.0001);
    }

    @Test
    public void testLimpar() {
        Memoria memoria = new Memoria();

        memoria.armazenar(10.0);
        memoria.limpar();

        assertEquals(0.0, memoria.obterValor(), 0.0001);
    }
}

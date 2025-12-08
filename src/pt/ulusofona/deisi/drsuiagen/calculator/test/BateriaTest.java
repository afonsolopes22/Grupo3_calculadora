package pt.ulusofona.deisi.drsuiagen.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BateriaTest {

    @Test
    public void testGastarNaoPassaDeZero() {
        Bateria bateria = new Bateria(10);

        bateria.gastar(5);
        assertEquals(5.0, bateria.verificarNivel(), 0.0001);

        bateria.gastar(10);
        assertEquals(0.0, bateria.verificarNivel(), 0.0001);
    }

    @Test
    public void testNivelInicialLimitadoA100() {
        Bateria bateria = new Bateria(150);

        assertEquals(100.0, bateria.verificarNivel(), 0.0001);
    }
}

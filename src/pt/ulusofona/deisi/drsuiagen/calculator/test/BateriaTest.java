package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.*;

import static org.junit.jupiter.api.Assertions.*;

public class BateriaTest {

    private Calculadora novaCalcComBateria(double nivelInicial) {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(nivelInicial);
        return new Calculadora(visor, memoria, bateria);
    }

    @Test
    public void testCadaOperacaoGasta5DeBateria() {
        Bateria bateria = new Bateria(100);
        Calculadora calc = new Calculadora(new Visor(), new Memoria(), bateria);

        calc.adicionar(1, 1);   // -5
        assertEquals(95, bateria.verificarNivel(), 0.0001);

        calc.subtrair(5, 2);   // -5
        assertEquals(90, bateria.verificarNivel(), 0.0001);

        calc.multiplicar(3, 3);   // -5
        assertEquals(85, bateria.verificarNivel(), 0.0001);

        calc.dividir(10, 2);   // -5
        assertEquals(80, bateria.verificarNivel(), 0.0001);
    }

    @Test
    public void testDividirPorZeroTambemGastaBateria() {
        Bateria bateria = new Bateria(100);
        Visor visor = new Visor();
        Calculadora calc = new Calculadora(visor, new Memoria(), bateria);

        double res = calc.dividir(10, 0);

        assertTrue(Double.isNaN(res));
        assertEquals(95, bateria.verificarNivel(), 0.0001);
    }

    @Test
    public void testBateriaNaoFicaNegativa() {
        Bateria bateria = new Bateria(5);
        Calculadora calc = new Calculadora(new Visor(), new Memoria(), bateria);

        calc.adicionar(1, 1);  // bateria iria para 0
        assertEquals(0, bateria.verificarNivel(), 0.0001);

        calc.subtrair(3, 1);   // bateria já está a 0 → não deve ficar negativo
        assertEquals(0, bateria.verificarNivel(), 0.0001);
    }




        @Test
        public void testBateriaDesceAoDesenharGrafico() {

            // Arrange
            Visor visor = new Visor();
            Memoria memoria = new Memoria();
            Bateria bateria = new Bateria(100);
            Calculadora calc = new Calculadora(visor, memoria, bateria);

            calc.gastarOperacaoExtra();

            assertEquals(95.0, calc.getNivelBateria(), 0.0001);

            assertTrue(
                    visor.getValor().startsWith("Operação realizada"),
                    "Esperava mensagem no visor, mas obtive: " + visor.getValor()
            );
        }
    }



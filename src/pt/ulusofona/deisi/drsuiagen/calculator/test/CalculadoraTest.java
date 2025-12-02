package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.Bateria;
import pt.ulusofona.deisi.drsuiagen.calculator.Calculadora;
import pt.ulusofona.deisi.drsuiagen.calculator.Memoria;
import pt.ulusofona.deisi.drsuiagen.calculator.Visor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    private Calculadora novaCalculadora() {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(100);
        return new Calculadora(visor, memoria, bateria);
    }

    @Test
    public void testAdicionar() {
        Calculadora calc = novaCalculadora();

        double res = calc.adicionar(2, 3);

        assertEquals(5.0, res, 0.0001);
    }

    @Test
    public void testSubtrair() {
        Calculadora calc = novaCalculadora();

        double res = calc.subtrair(10, 4);

        assertEquals(6.0, res, 0.0001);
    }

    @Test
    public void testMultiplicar() {
        Calculadora calc = novaCalculadora();

        double res = calc.multiplicar(3, 5);

        assertEquals(15.0, res, 0.0001);
    }

    @Test
    public void testDividir() {
        Calculadora calc = novaCalculadora();

        double res = calc.dividir(20, 4);

        assertEquals(5.0, res, 0.0001);
    }

    @Test
    public void testDividirPorZero() {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(100);
        Calculadora calc = new Calculadora(visor, memoria, bateria);

        double res = calc.dividir(10, 0);

        assertTrue(Double.isNaN(res));
        assertEquals("Erro: divisão por zero", visor.getValor());
    }
}

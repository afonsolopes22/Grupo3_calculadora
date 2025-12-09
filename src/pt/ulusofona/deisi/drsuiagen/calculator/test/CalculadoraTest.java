package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    Calculadora novaCalculadora() {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(100);
        return new Calculadora(visor, memoria, bateria);
    }

    @Test
    public void testSomar() {
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

        assertTrue(
                visor.getValor().startsWith("Erro: divisão por zero"),
                "Mensagem errada no visor: " + visor.getValor()
        );
    }

    @Test
    public void testPotencia() {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(100);
        Calculadora calc = new Calculadora(visor, memoria, bateria);

        double res = calc.potencia(2, 3);

        assertEquals(8.0, res, 0.0001);
        assertEquals(95.0, calc.getNivelBateria(), 0.0001);
        assertTrue(visor.getValor().startsWith("8"));
    }

    @Test
    public void testDesenharGraficoGastaBateria() {
        Visor visor = new Visor();
        Memoria memoria = new Memoria();
        Bateria bateria = new Bateria(100);
        Calculadora calc = new Calculadora(visor, memoria, bateria);

        // simula a operação de “desenhar gráfico”
        calc.gastarOperacaoExtra();

        assertEquals(95.0, calc.getNivelBateria(), 0.0001);

        assertTrue(
                visor.getValor().startsWith("Operação realizada"),
                "Esperava mensagem no visor, obtive: " + visor.getValor()
        );
    }
}

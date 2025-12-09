package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.Botao;

import static org.junit.jupiter.api.Assertions.*;

public class BotaoTest {

    @Test
    public void testGetters() {
        Botao botao = new Botao("1", "numero");

        assertEquals("1", botao.getTexto());
        assertEquals("numero", botao.getTipo());
    }

    @Test
    public void testPressionarNaoLancaExcecao() {
        Botao botao = new Botao("=", "operacao");

        assertDoesNotThrow(botao::pressionar);
    }
}

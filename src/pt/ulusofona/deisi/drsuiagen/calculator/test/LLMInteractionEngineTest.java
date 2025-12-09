package pt.ulusofona.deisi.drsuiagen.calculator.test;

import org.junit.jupiter.api.Test;
import pt.ulusofona.deisi.drsuiagen.calculator.LLMInteractionEngine;

import static org.junit.jupiter.api.Assertions.*;

public class LLMInteractionEngineTest {

    @Test
    public void testSendPromptGeraExplicacaoCorretaParaSoma() {
        String url = "https://modelos.ai.ulusofona.pt/v1/completions";
        String apiKey = "chave-teste";
        String model = "gpt-4-turbo";
        boolean useHack = false;

        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);

        String prompt = "Explica passo a passo o cálculo da expressão '2 + 3', " +
                "cujo resultado é 5. Primeiro indica o resultado e depois explica cada passo.";

        String resposta = engine.sendPrompt(prompt);

        assertFalse(resposta.contains("Não consegui analisar a expressão"));

        assertTrue(resposta.contains("O resultado da expressão é 5.0"));

        assertTrue(resposta.contains("2.0"), "Esperava ver o 2.0 na explicação");
        assertTrue(resposta.contains("3.0"), "Esperava ver o 3.0 na explicação");

        assertTrue(resposta.contains("2.0 + 3.0 = 5.0"));
    }
}

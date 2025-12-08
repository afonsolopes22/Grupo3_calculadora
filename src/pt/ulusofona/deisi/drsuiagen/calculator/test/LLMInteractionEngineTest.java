package pt.ulusofona.deisi.drsuiagen.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LLMInteractionEngineTest {

    @Test
    public void testSendPromptIncluiPromptOriginal() {
        String url = "https://modelos.ai.ulusofona.pt/v1/completions";
        String apiKey = "chave-teste";
        String model = "gpt-4-turbo";
        boolean useHack = false;

        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);

        String prompt = "Explica passo a passo o cálculo da expressão '1 + 3'";
        String resposta = engine.sendPrompt(prompt);

        assertTrue(resposta.contains(prompt));
        assertTrue(resposta.contains(url));
        assertTrue(resposta.contains(model));
    }
}

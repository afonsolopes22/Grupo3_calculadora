package pt.ulusofona.deisi.drsuiagen.calculator;

/**
Feito por AI

 Imprimir com varias casas decimais

 */

//

public class LLMInteractionEngine {

    private final String url;
    private final String apiKey;
    private final String model;
    private final boolean useHack;

    public LLMInteractionEngine(String url, String apiKey, String model, boolean useHack) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
        this.useHack = useHack;
    }

    public String sendPrompt(String prompt) {
        // SIMULAÇÃO – NÃO FAZ PEDIDO HTTP REAL
        StringBuilder sb = new StringBuilder();
        sb.append("=== SIMULAÇÃO DE RESPOSTA DO LLM ===\n");
        sb.append("URL: ").append(url).append("\n");
        sb.append("Model: ").append(model).append("\n");
        sb.append("Prompt recebido:\n");
        sb.append(prompt).append("\n");
        sb.append("=== FIM DA SIMULAÇÃO ===");
        return sb.toString();
    }
}

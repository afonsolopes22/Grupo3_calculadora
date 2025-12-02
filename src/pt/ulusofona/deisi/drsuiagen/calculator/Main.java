package pt.ulusofona.deisi.drsuiagen.calculator;

public class Main {

    static String apiKey = "a very nice secret";
    static String url = "https://modelos.ai.ulusofona.pt/v1/completions";
    static String model = "gpt-4-turbo";
    static boolean useHack = false;

    public static void main(String[] args) throws Exception {
        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);
        LLMCalculator calculator = new LLMCalculator(engine);
        calculator.execute();
    }
}

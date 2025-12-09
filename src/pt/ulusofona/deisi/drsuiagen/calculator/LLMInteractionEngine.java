package pt.ulusofona.deisi.drsuiagen.calculator;

/**
 * "LLM" simulado:
 * Lê a expressão e o resultado do prompt
 * e gera uma explicação passo a passo em português.
 */
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
        // Tenta extrair a expressão 'a op b' e o resultado do texto do prompt
        String expressao = extrairExpressao(prompt);
        String resultadoStr = extrairResultado(prompt);

        if (expressao == null || resultadoStr == null) {
            return "Não consegui analisar a expressão no prompt.\n\nPrompt original:\n" + prompt;
        }

        String[] partes = expressao.trim().split("\\s+");
        if (partes.length != 3) {
            return "Expressão num formato inesperado: '" + expressao + "'.";
        }

        String aStr = partes[0];
        String opStr = partes[1];
        String bStr = partes[2];

        double a, b, resultado;
        try {
            a = Double.parseDouble(aStr);
            b = Double.parseDouble(bStr);
            resultado = Double.parseDouble(resultadoStr);
        } catch (NumberFormatException e) {
            return "Não consegui interpretar os números na expressão '" + expressao +
                    "' com resultado '" + resultadoStr + "'.";
        }

        char op = opStr.charAt(0);

        StringBuilder sb = new StringBuilder();
        sb.append("O resultado da expressão é ").append(resultado).append(".\n\n");
        sb.append("Passos do cálculo:\n");

        switch (op) {
            case '+':
                sb.append("1) Começamos com o primeiro número: ").append(a).append(".\n");
                sb.append("2) Somamos o segundo número: ").append(b).append(".\n");
                sb.append("3) ").append(a).append(" + ").append(b)
                        .append(" = ").append(resultado).append(".\n");
                break;

            case '-':
                sb.append("1) Começamos com o primeiro número: ").append(a).append(".\n");
                sb.append("2) Subtraímos o segundo número: ").append(b).append(".\n");
                sb.append("3) ").append(a).append(" - ").append(b)
                        .append(" = ").append(resultado).append(".\n");
                break;

            case '*':
                sb.append("1) Começamos com o primeiro número: ").append(a).append(".\n");
                sb.append("2) Multiplicamos pelo segundo número: ").append(b).append(".\n");
                sb.append("3) ").append(a).append(" × ").append(b)
                        .append(" = ").append(resultado).append(".\n");
                break;

            case '/':
                sb.append("1) Começamos com o primeiro número: ").append(a).append(".\n");
                sb.append("2) Dividimos pelo segundo número: ").append(b).append(".\n");
                sb.append("3) ").append(a).append(" ÷ ").append(b)
                        .append(" = ").append(resultado).append(".\n");
                break;

            case '^':
                sb.append("1) Identificamos a base: ").append(a).append(".\n");
                sb.append("2) Identificamos o expoente: ").append(b).append(".\n");
                sb.append("3) Calculamos a potência: ").append(a).append("^").append(b)
                        .append(" = ").append(resultado).append(".\n");
                break;

            default:
                sb.append("Não reconheço o operador '").append(op).append("'.\n");
        }

        sb.append("\nExplicação gerada de forma simples em português de Portugal.");
        return sb.toString();
    }

    // ---------- Helpers para extrair dados do prompt ----------

    private String extrairExpressao(String prompt) {
        String marcadorInicio = "expressão '";
        String marcadorFim = "', cujo resultado";

        int start = prompt.indexOf(marcadorInicio);
        if (start == -1) return null;

        start += marcadorInicio.length();
        int end = prompt.indexOf(marcadorFim, start);
        if (end == -1) return null;

        return prompt.substring(start, end);
    }

    private String extrairResultado(String prompt) {
        String marcadorInicio = "cujo resultado é ";

        int start = prompt.indexOf(marcadorInicio);
        if (start == -1) return null;

        start += marcadorInicio.length();
        int end = prompt.indexOf(".", start);
        if (end == -1) {
            end = prompt.length();
        }

        return prompt.substring(start, end).trim();
    }
}

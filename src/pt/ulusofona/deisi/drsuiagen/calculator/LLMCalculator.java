package pt.ulusofona.deisi.drsuiagen.calculator;

import java.util.Scanner;

public class LLMCalculator {

    private LLMInteractionEngine engine;
    private Calculadora calculadora;
    private Scanner scanner;

    private double ultimoResultado = 0.0;
    private String ultimaExpressao = "";

    public LLMCalculator(LLMInteractionEngine engine) {
        this.engine = engine;
        this.calculadora = new Calculadora(new Visor(), new Memoria(), new Bateria(100));
        this.scanner = new Scanner(System.in);
    }

    public void execute() throws Exception {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> fazerOperacaoComExplicacao();
                case 2 -> desenharEquacao2Grau();
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida.");
            }

            System.out.println();

        } while (opcao != 0);
    }

    private void mostrarMenu() {
        double nivelBateria = calculadora.getNivelBateria();

        System.out.printf("===== CALCULADORA + LLM =====%25s%n",
                "Bateria: " + (int) nivelBateria + "%");
        System.out.println("1 - Fazer operação e pedir explicação ao LLM");
        System.out.println("2 - Desenhar gráfico de equação de 2.º grau");
        System.out.println("0 - Sair");
    }

    // ------------------ OPÇÃO 1: operação normal + explicação ------------------

    private void fazerOperacaoComExplicacao() throws Exception {
        double a = lerDouble("Primeiro número: ");
        double b = lerDouble("Segundo número: ");
        char op = lerChar("Operação (+, -, *, /, ^): ");

        double resultado;

        switch (op) {
            case '+' -> resultado = calculadora.adicionar(a, b);
            case '-' -> resultado = calculadora.subtrair(a, b);
            case '*' -> resultado = calculadora.multiplicar(a, b);
            case '/' -> resultado = calculadora.dividir(a, b);
            case '^' -> resultado = calculadora.potencia(a, b);
            default -> {
                System.out.println("Operação inválida.");
                return;
            }
        }

        ultimoResultado = resultado;
        ultimaExpressao = a + " " + op + " " + b;

        System.out.println("Resultado numérico: " + ultimoResultado);

        String prompt = "Explica passo a passo o cálculo da expressão '" +
                ultimaExpressao + "', cujo resultado é " + ultimoResultado +
                ". Primeiro indica o resultado e depois explica cada passo do cálculo, " +
                "em linguagem simples e em português de Portugal.";

        String resposta = engine.sendPrompt(prompt);

        System.out.println("\n=== Explicação do LLM ===");
        System.out.println(resposta);
    }

    // ------------------ OPÇÃO 2: equação de 2.º grau + gráfico ASCII ------------------

    private void desenharEquacao2Grau() {
        // esta operação também gasta bateria
        calculadora.gastarOperacaoExtra();

        System.out.println("\n--- Equação de 2.º grau: f(x) = a·x^2 + b·x + c ---");

        double a = lerDouble("Coeficiente a: ");
        double b = lerDouble("Coeficiente b: ");
        double c = lerDouble("Coeficiente c: ");

        System.out.println("\nEquação: f(x) = " + a + "·x^2 + " + b + "·x + " + c);
        System.out.println("\nGráfico aproximado (ASCII):\n");

        plotQuadratica(a, b, c);
    }

    /**
     * Desenha um gráfico ASCII aproximado da função f(x) = a x^2 + b x + c
     * no intervalo x ∈ [-10, 10].
     */
    public void plotQuadratica(double a, double b, double c) {
        int width = 60;   // número de colunas
        int height = 20;  // número de linhas

        double xmin = -10;
        double xmax = 10;

        double[] yvals = new double[width];
        double ymin = Double.POSITIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;

        // calcular valores de y e mínimos/máximos
        for (int col = 0; col < width; col++) {
            double x = xmin + col * (xmax - xmin) / (width - 1);
            double y = a * x * x + b * x + c;
            yvals[col] = y;
            if (y < ymin) ymin = y;
            if (y > ymax) ymax = y;
        }

        // evitar intervalo demasiado pequeno
        if (Math.abs(ymax - ymin) < 1e-6) {
            ymax = ymin + 1;
        }

        // desenhar linha a linha (de cima para baixo)
        for (int row = 0; row < height; row++) {
            double yline = ymax - row * (ymax - ymin) / (height - 1);

            StringBuilder sb = new StringBuilder();

            for (int col = 0; col < width; col++) {
                double y = yvals[col];

                double passo = (ymax - ymin) / height;
                if (Math.abs(y - yline) < passo / 2) {
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }

            System.out.println(sb);
        }

        System.out.println("\n(Gráfico apenas ilustrativo, em modo texto.)");
    }

    // ------------------ Helpers de input ------------------

    private int lerInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + msg);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // limpar newline
        return value;
    }

    private double lerDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido. " + msg);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private char lerChar(String msg) {
        System.out.print(msg);
        String line = scanner.nextLine();
        while (line.isEmpty()) {
            System.out.print("Valor inválido. " + msg);
            line = scanner.nextLine();
        }
        return line.charAt(0);
    }
}

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
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida.");
            }

            System.out.println();

        } while (opcao != 0);
    }

    private void mostrarMenu() {
        System.out.println("===== CALCULADORA + LLM =====");
        System.out.println("1 - Fazer operação e pedir explicação ao LLM");
        System.out.println("0 - Sair");
    }

    private void fazerOperacaoComExplicacao() throws Exception {
        double a = lerDouble("Primeiro número: ");
        double b = lerDouble("Segundo número: ");
        char op = lerChar("Operação (+, -, *, /): ");

        double resultado;

        switch (op) {
            case '+' -> resultado = calculadora.adicionar(a, b);
            case '-' -> resultado = calculadora.subtrair(a, b);
            case '*' -> resultado = calculadora.multiplicar(a, b);
            case '/' -> resultado = calculadora.dividir(a, b);
            default -> {
                System.out.println("Operação inválida.");
                return;
            }
        }

        ultimoResultado = resultado;
        ultimaExpressao = a + " " + op + " " + b;

        System.out.println("Resultado numérico: " + ultimoResultado);

        // Prompt para o LLM: expressão + resultado
        String prompt = "Explica passo a passo o cálculo da expressão '" +
                ultimaExpressao + "', cujo resultado é " + ultimoResultado +
                ". Primeiro indica o resultado e depois explica cada passo do cálculo, " +
                "em linguagem simples e em português de Portugal.";

        String resposta = engine.sendPrompt(prompt);

        System.out.println("\n=== Explicação do LLM ===");
        System.out.println(resposta);
    }

    // ------- Helpers de input -------

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

package app;

import Calculator.CalculatorService;
import History.HistoryManager;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Application {
    private static final int MILLION = 1000000;
    private static final int ZERO = 0;
    private static final String BIGGER_MILLION = "ОШИБКА: число больше миллиона";
    private static final String DIVISION_ZERO = "ОШИБКА: деление на ноль.";
    private static final String NEGATIVE_RESULT = "ОШИБКА: результат отрицательный.";
    private static final boolean IS_RUNNING = true;
    private static final String PROMPT = "> ";
    private static final String EXIT_COMMAND = "exit";
    private static final String GET_HISTORY = "history";
    private static final String INFO = "help";
    private static final String LAST = "last";
    private static final String CLEAR_HISTORY = "clear";
    private static final String RESULT_FORMAT = " = ";
    private static final int PARTS_COUNT = 3;
    private static final int PART_LEFT_NUMBER = 0;
    private static final int PART_OPERATOR = 1;
    private static final int PART_RIGHT_NUMBER = 2;

    private final ArrayList<String> history = new ArrayList<>();
    private final CalculatorService calc = new CalculatorService();
    private final Scanner scanner = new Scanner(System.in);
    private final HistoryManager historyManager = new HistoryManager();

    public void run() {
        while (IS_RUNNING) {
            System.out.print(PROMPT);
            if (!scanner.hasNextLine()) {break;}

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {continue;}
            if (EXIT_COMMAND.equals(input)) {break;}
            if (handleCommand(input)) {continue;}

            processInput(input);
        }
    }

    private void processInput(String input) {
        try {
            String[] parts = input.split("\\s+");
            if (parts.length != PARTS_COUNT) {
                System.out.println(CalculatorService.ERR_INVALID_FORMAT);
                return;
            }
            double firstNumber = Double.parseDouble(parts[PART_LEFT_NUMBER]);
            double secondNumber = Double.parseDouble(parts[PART_RIGHT_NUMBER]);
            String operator = parts[PART_OPERATOR];
            double result = calc.calculate(firstNumber, secondNumber, operator);
            if (result < ZERO) {
                System.out.println(NEGATIVE_RESULT+"\n");
                return;
            }
            if (result > MILLION){
                System.out.print(BIGGER_MILLION+"\n");

                return;
            }
            printAndRemember(input + RESULT_FORMAT + result);
        } catch (ArithmeticException e) {
            System.out.println(DIVISION_ZERO);
        } catch (NumberFormatException e) {
            System.out.println(CalculatorService.ERR_INVALID_FORMAT);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAndRemember(String output) {
        historyManager.addToHistory(history, output);
        System.out.println(output);
    }

    private boolean handleCommand(String input) {
        switch (input) {
            case GET_HISTORY:
                printHistory();
                return true;
            case INFO:
                printHelp();
                return true;
            case LAST:
                printLastHistoryEntry();
                return true;
            case CLEAR_HISTORY:
                printClearHistory();
                return true;
            default:
                return false;
        }
    }

    private void printHistory(){
        historyManager.historyGet(history);
        System.out.println();
    }
    private void printHelp(){
        historyManager.HelpMenu();
        System.out.println();
    }
    private void printLastHistoryEntry(){
        historyManager.last(history);
        System.out.println();
    }
    private void printClearHistory() {
        historyManager.clearHistory(history);
        System.out.println();
    }
}


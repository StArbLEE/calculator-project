package Calculator;

import Operations.Operation;
import Operations.Operation.AddOperation;
import Operations.Operation.SubtractOperation;
import Operations.Operation.MultiplyOperation;
import Operations.Operation.DivideOperation;

import java.util.HashMap;
import java.util.Map;

public class CalculatorService {
    public static final String ERR_INVALID_FORMAT = "ОШИБКА: неверный формат. Используйте: число оператор число";

    private Map<String, Operation> operations = new HashMap<>();

    public CalculatorService() {
        operations.put("+", new AddOperation());
        operations.put("-", new SubtractOperation());
        operations.put("*", new MultiplyOperation());
        operations.put("/", new DivideOperation());
    }
    public double calculate(double firstNumber, double secondNumber, String operator) {
        Operation operation = operations.get(operator);

        if (operation == null) {
            throw new IllegalArgumentException(ERR_INVALID_FORMAT);
        }

        return operation.execute(firstNumber, secondNumber);
    }
    private double divide(double firstNumber, double secondNumber) {
        if (secondNumber == ZERO) {
            throw new ArithmeticException();
        }
        return firstNumber / secondNumber;
    }
}

package service;

import Calculator.CalculatorService;
import Operations.Operation;
import Operations.Operation.AddOperation;
import Operations.Operation.DivideOperation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    void addOperationWorks() {
        Operation op = new AddOperation();
        assertEquals(8.0, op.execute(5, 3), 0.001);
    }

    @Test
    void divideOperationWorks() {
        Operation op = new DivideOperation();
        assertEquals(2.5, op.execute(5, 2), 0.001);
    }

    @Test
    void divideByZeroThrows() {
        Operation op = new DivideOperation();
        assertThrows(ArithmeticException.class, () -> op.execute(5, 0));
    }

    @Test
    void unknownOperatorThrows() {
        CalculatorService calc = new CalculatorService();
        assertThrows(IllegalArgumentException.class,
                () -> calc.calculate(5, 3, "%"));
    }
}
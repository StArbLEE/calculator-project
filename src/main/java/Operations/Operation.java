package Operations;

public interface Operation {
    double execute(double a, double b);


    class AddOperation implements Operation {
        @Override
        public double execute(double a, double b) { return a + b; }
    }

    class SubtractOperation implements Operation {
        @Override
        public double execute(double a, double b) { return a - b; }
    }

    class MultiplyOperation implements Operation {
        @Override
        public double execute(double a, double b) { return a * b; }
    }

    class DivideOperation implements Operation {
        @Override
        public double execute(double a, double b) {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        }
    }
}

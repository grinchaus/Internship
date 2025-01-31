package expression.generic;

import expression.exceptions.ArithmeticException;

public class ByteSolve implements GenericInterface<Byte> {
    public Byte add(Byte x, Byte y) {
        return (byte) (x + y);
    }

    public Byte subtract(Byte x, Byte y) {
        return (byte) (x - y);
    }

    public Byte multiply(Byte x, Byte y) {
        return (byte) (x * y);
    }

    public Byte divide(Byte x, Byte y) {
        if (y == 0) throw new ArithmeticException("second argument is 0");
        return (byte) (x / y);
    }

    public Byte negate(Byte x) {
        return (byte) (-x);
    }

    public Byte numberToGeneric(String x) {
        return (byte) Integer.parseInt(x);
    }
}

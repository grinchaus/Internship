package expression.generic;

import expression.exceptions.ArithmeticException;

public class IntegerSolve implements GenericInterface<Integer> {
    public Integer add(Integer x, Integer y) {
        if (x > 0 && y > 0 && Integer.MAX_VALUE - x < y) throw new ArithmeticException("overflow");
        if (x < 0 && y < 0 && Integer.MIN_VALUE - x >= y + 1) throw new ArithmeticException("overflow");
        return x + y;
    }

    public Integer subtract(Integer x, Integer y) {
        if ((x >= 0 && y < 0 && Integer.MAX_VALUE + y < x) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE + y > x)) throw new ArithmeticException("overflow");
        return x - y;
    }

    public Integer multiply(Integer x, Integer y) {
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) throw new ArithmeticException("overflow");
        if (x < 0 && y < 0 && x < Integer.MAX_VALUE / y) throw new ArithmeticException("overflow");
        if (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) throw new ArithmeticException("overflow");
        if (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) throw new ArithmeticException("overflow");
        return x * y;
    }

    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new ArithmeticException("second argument is 0");
        if (x == Integer.MIN_VALUE && y == -1) throw new ArithmeticException("overflow");
        return x / y;
    }

    public Integer negate(Integer x) {
        if (x == -2147483648) throw new ArithmeticException("overflow");
        return -x;
    }

    public Integer numberToGeneric(String x) {
        try {
            return Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new ArithmeticException("overflow " + x);
        }
    }
}

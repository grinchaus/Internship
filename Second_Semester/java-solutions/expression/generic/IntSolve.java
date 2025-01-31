package expression.generic;

import expression.exceptions.ArithmeticException;

public class IntSolve implements GenericInterface<Integer> {
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new ArithmeticException("second argument is 0");
        return x / y;
    }

    public Integer negate(Integer x) {
        return -x;
    }

    public Integer numberToGeneric(String x) {
        return Integer.parseInt(x);
    }
}

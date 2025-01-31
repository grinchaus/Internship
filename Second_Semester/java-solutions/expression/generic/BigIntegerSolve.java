package expression.generic;

import expression.exceptions.ArithmeticException;

import java.math.BigInteger;

public class BigIntegerSolve implements GenericInterface<BigInteger> {
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.equals(new BigInteger("0"))) throw new ArithmeticException("second argument is 0");
        return x.divide(y);
    }

    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    public BigInteger numberToGeneric(String x) {
        try {
            return new BigInteger(x);
        } catch (NumberFormatException e) {
            throw new ArithmeticException("overflow " + x);
        }
    }
}

package expression.generic;

import expression.exceptions.ArithmeticException;

public class GenericNegate<T> implements GenericGlobalExpression<T> {
    private final GenericGlobalExpression<T> expression;
    private final GenericInterface<T> genericType;

    public GenericNegate(GenericGlobalExpression<T> expression, GenericInterface<T> genericType) {
        this.expression = expression;
        this.genericType = genericType;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return genericType.negate(expression.evaluate(x, y, z));
    }
}

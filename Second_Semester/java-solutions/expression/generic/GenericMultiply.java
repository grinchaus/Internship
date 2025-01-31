package expression.generic;

public class GenericMultiply<T> extends GenericAbstractClass<T> {
    public GenericMultiply(GenericGlobalExpression<T> eval1, GenericGlobalExpression<T> eval2, GenericInterface<T> genericType) {
        super(eval1, eval2, genericType);
    }

    @Override
    protected String getOperator() {
        return "*";
    }

    @Override
    protected T eval(T x, T y) {
        return genericType.multiply(x, y);
    }
}
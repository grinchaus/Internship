package expression.generic;

public class GenericAdd<T> extends GenericAbstractClass<T> {
    public GenericAdd(GenericGlobalExpression<T> eval1, GenericGlobalExpression<T> eval2, GenericInterface<T> genericType) {
        super(eval1, eval2, genericType);
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    protected T eval(T x, T y) {
        return genericType.add(x, y);
    }
}
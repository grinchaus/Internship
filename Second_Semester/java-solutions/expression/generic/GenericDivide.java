package expression.generic;

public class GenericDivide<T> extends GenericAbstractClass<T> {
    public GenericDivide(GenericGlobalExpression<T> eval1, GenericGlobalExpression<T> eval2, GenericInterface<T> genericType) {
        super(eval1, eval2, genericType);
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    protected T eval(T x, T y) {
        return genericType.divide(x, y);
    }
}
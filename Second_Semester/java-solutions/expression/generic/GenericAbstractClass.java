package expression.generic;

import java.util.Objects;

public abstract class GenericAbstractClass<T> implements GenericGlobalExpression<T> {
    private final GenericGlobalExpression<T> eval1;
    private final GenericGlobalExpression<T> eval2;
    protected final GenericInterface<T> genericType;

    public GenericAbstractClass(GenericGlobalExpression<T> eval1, GenericGlobalExpression<T> eval2, GenericInterface<T> genericType) {
        this.eval1 = eval1;
        this.eval2 = eval2;
        this.genericType = genericType;
    }

    protected abstract String getOperator();

    protected abstract T eval(T x, T y);

    @Override
    public String toString() {
        return "(" + eval1.toString() + " " + getOperator() + " " + eval2.toString() + ")";
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return eval(eval1.evaluate(x, y, z), eval2.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericAbstractClass<?> that = (GenericAbstractClass<?>) o;
        return Objects.equals(eval1, that.eval1) && Objects.equals(eval2, that.eval2) && Objects.equals(genericType, that.genericType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eval1, eval2, getOperator()) * 13;
    }
}
package expression.generic;

public interface GenericGlobalExpression<T> {
    String toString();

    T evaluate(T x, T y, T z);
}

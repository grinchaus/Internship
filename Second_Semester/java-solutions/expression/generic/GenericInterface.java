package expression.generic;

public interface GenericInterface<T> {
    T add(T x, T y);

    T subtract(T x, T y);

    T multiply(T x, T y);

    T divide(T x, T y);

    T negate(T x);

    T numberToGeneric(String x);
}

package expression.generic;

import java.util.Objects;

public class GenericVariable<T> implements GenericGlobalExpression<T> {
    private final String value;

    public GenericVariable(String value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericVariable<?> variable = (GenericVariable<?>) o;
        return Objects.equals(value, variable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value) * 23;
    }
}
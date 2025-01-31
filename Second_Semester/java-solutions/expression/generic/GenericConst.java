package expression.generic;

import java.util.Objects;

public class GenericConst<T> implements GenericGlobalExpression<T> {
    private final T con;

    public GenericConst(T con) {
        this.con = con;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return con;
    }

    @Override
    public String toString() {
        return con.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericConst<?> aConst = (GenericConst<?>) o;
        return Objects.equals(con, aConst.con);
    }

    @Override
    public int hashCode() {
        return Objects.hash(con) * 17;
    }
}
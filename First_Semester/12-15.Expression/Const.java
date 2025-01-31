package expression;
import java.util.Objects;
public class Const implements GlobalExpression{
    private final int con;
    public Const(int con){
        this.con = con;
    }
    @Override
    public int evaluate(int x) {
        return con;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return con;
    }
    @Override
    public String toString() {
        return Integer.toString(con);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return con == aConst.con;
    }
    @Override
    public int hashCode() {
        return Objects.hash(con)*17;
    }
}
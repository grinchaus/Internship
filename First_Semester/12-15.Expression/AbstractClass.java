package expression;
import java.util.Objects;
public abstract class AbstractClass implements GlobalExpression{
    private final GlobalExpression eval1;
    private final GlobalExpression eval2;
    public AbstractClass(GlobalExpression eval1, GlobalExpression eval2){
        this.eval1 = eval1;
        this.eval2 = eval2;
    }
    protected abstract String getOperator();
    protected abstract int eval(int x, int y);
    @Override
    public String toString() {
        return "(" + eval1.toString() + " " + getOperator() + " " + eval2.toString() + ")";
    }
    @Override
    public int evaluate(int x) {
        return eval(eval1.evaluate(x),eval2.evaluate(x));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return eval(eval1.evaluate(x,y,z),eval2.evaluate(x,y,z));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractClass that = (AbstractClass) o;
        return Objects.equals(eval1, that.eval1) && Objects.equals(eval2, that.eval2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eval1, eval2, getOperator())*13;
    }
}
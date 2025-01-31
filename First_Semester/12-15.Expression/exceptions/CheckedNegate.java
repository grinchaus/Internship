package expression.exceptions;
import expression.GlobalExpression;
public class CheckedNegate implements GlobalExpression {
    private final GlobalExpression expression;
    public CheckedNegate(GlobalExpression expression) {
        this.expression = expression;
    }
    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }
    @Override
    public int evaluate(int x) {
        int check = expression.evaluate(x);
        if (check == Integer.MIN_VALUE) throw new ArithmeticExceptionClass("overflow");
        return -1 * check;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        int check = expression.evaluate(x, y, z);
        if (check == Integer.MIN_VALUE) throw new ArithmeticExceptionClass("overflow");
        return -1 * check;
    }
}
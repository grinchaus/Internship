package expression.exceptions;
import expression.GlobalExpression;

import java.util.List;

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
        if (x == Integer.MIN_VALUE) throw new ArithmeticException("overflow");
        return -1 * x;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        int check = expression.evaluate(x, y, z);
        if (check == Integer.MIN_VALUE) throw new ArithmeticException("overflow");
        return -1 * check;
    }
    @Override
    public int evaluate(List<Integer> variables) {
        int check = expression.evaluate(variables);
        if (check == Integer.MIN_VALUE) throw new ArithmeticException("overflow");
        return -1 * check;
    }
}
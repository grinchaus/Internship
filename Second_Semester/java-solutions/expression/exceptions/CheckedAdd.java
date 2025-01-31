package expression.exceptions;
import expression.*;
public class CheckedAdd extends AbstractClass {
    public CheckedAdd(GlobalExpression eval1, GlobalExpression eval2) {
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "+";
    }
    @Override
    protected int eval(int x, int y) {
        if (x > 0 && y > 0 && Integer.MAX_VALUE - x < y) throw new ArithmeticException("overflow");
        if (x < 0 && y < 0 && Integer.MIN_VALUE - x >= y + 1) throw new ArithmeticException("overflow");
        return x + y;
    }
}
package expression.exceptions;
import expression.*;
public class CheckedDivide extends AbstractClass {
    public CheckedDivide(GlobalExpression eval1, GlobalExpression eval2) {
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "/";
    }
    @Override
    protected int eval(int x, int y) {
        if (y == 0) throw new ArithmeticException("second argument is 0");
        if (x == Integer.MIN_VALUE && y == -1) throw new ArithmeticException("overflow");
        return x / y;
    }
}
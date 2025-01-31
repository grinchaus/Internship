package expression.exceptions;
import expression.*;
public class CheckedMultiply extends AbstractClass {
    public CheckedMultiply(GlobalExpression eval1, GlobalExpression eval2) {
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "*";
    }
    @Override
    protected int eval(int x, int y) {
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) throw new ArithmeticException("overflow");
        if (x < 0 && y < 0 && x < Integer.MAX_VALUE / y) throw new ArithmeticException("overflow");
        if (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) throw new ArithmeticException("overflow");
        if (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) throw new ArithmeticException("overflow");
        return x * y;
    }
}
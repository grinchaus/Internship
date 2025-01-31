package expression.exceptions;
import expression.*;
public class CheckedSubtract extends AbstractClass {
    public CheckedSubtract(GlobalExpression eval1, GlobalExpression eval2) {
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "-";
    }
    @Override
    protected int eval(int x, int y) {
        if ((x >= 0 && y < 0 && Integer.MAX_VALUE + y < x) ||
                (x <= 0 && y > 0 && Integer.MIN_VALUE + y > x)) throw new ArithmeticException("overflow");
        return x - y;
    }
}
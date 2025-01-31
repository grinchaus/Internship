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
        int check = x * y;
        if (check != 0 && (check / x != y || check / y != x)) throw new ArithmeticExceptionClass("overflow");
        if ((x > 0 && y > 0 || x < 0 && y < 0) && check <= 0) throw new ArithmeticExceptionClass("overflow");
        if ((x < 0 && y > 0 || x > 0 && y < 0) && check >= 0) throw new ArithmeticExceptionClass("overflow");
        return check;
    }
}
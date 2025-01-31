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
        if (y == 0) throw new ArithmeticExceptionClass("2 argument is 0");
        int check = x / y;
        if ((x > 0 && y > 0 || x < 0 && y < 0) && check < 0) throw new ArithmeticExceptionClass("overflow");
        return check;
    }
}
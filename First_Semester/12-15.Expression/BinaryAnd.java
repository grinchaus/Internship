package expression;
public class BinaryAnd extends AbstractClass {
    public BinaryAnd(GlobalExpression eval1, GlobalExpression eval2) {
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "&";
    }
    @Override
    protected int eval(int x, int y) {
        return x & y;
    }
}
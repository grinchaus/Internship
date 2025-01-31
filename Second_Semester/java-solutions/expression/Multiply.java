package expression;
public class Multiply extends AbstractClass {
    public Multiply(GlobalExpression eval1, GlobalExpression eval2){
        super(eval1, eval2);
    }
    @Override
    protected String getOperator() {
        return "*";
    }
    @Override
    protected int eval(int x, int y) {
        return x * y;
    }
}
package expression;

public class LowBits implements GlobalExpression {
    private final GlobalExpression expression;

    public LowBits(GlobalExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "t0 " + expression.toString();
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfTrailingZeros(expression.evaluate(x));
    }
    @Override
    public int evaluate(int x,int y,int z) {
        return Integer.numberOfTrailingZeros(expression.evaluate(x,y,z));
    }
}

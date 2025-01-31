package expression;

import java.util.List;

public class UMinus implements GlobalExpression {
    private final GlobalExpression expression;

    public UMinus(GlobalExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }
    @Override
    public int evaluate(int x,int y,int z) {
        return -1 * expression.evaluate(x,y,z);
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return -1 * expression.evaluate(variables);
    }
}

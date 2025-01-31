package expression;

import java.util.List;

public class HighBits implements GlobalExpression {
    private final GlobalExpression expression;

    public HighBits(GlobalExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "l0(" + expression.toString()+")";
    }

    @Override
    public int evaluate(int x) {
        int check = expression.evaluate(x);
        if (check == 0){
            return 32;
        }
        String binary = Integer.toBinaryString(check);
        return (32 - binary.length());
    }
    @Override
    public int evaluate(int x,int y,int z) {
        int check = expression.evaluate(x,y,z);
        if (check == 0){
            return 32;
        }
        String binary = Integer.toBinaryString(check);
        return (32 - binary.length());
    }

    @Override
    public int evaluate(List<Integer> variables) {
        int check = expression.evaluate(variables);
        if (check == 0){
            return 32;
        }
        String binary = Integer.toBinaryString(check);
        return (32 - binary.length());
    }
}

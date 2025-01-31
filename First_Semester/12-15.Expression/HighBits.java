package expression;

public class HighBits implements GlobalExpression {
    private final GlobalExpression expression;

    public HighBits(GlobalExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "l0 " + expression.toString();
    }

    @Override
    public int evaluate(int x) {
        int check = expression.evaluate(x);
        String binary = Integer.toBinaryString(check);
        return (32 - binary.length());
    }
    @Override
    public int evaluate(int x,int y,int z) {
        int check = expression.evaluate(x,y,z);
        String binary = Integer.toBinaryString(check);
        return (32 - binary.length());
    }
}

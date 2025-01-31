package expression;
import java.util.List;
import java.util.Objects;
public class Variable implements GlobalExpression{
    private final String value;
    private final int number;

    public Variable(String value){
        this.value = value;
        number = 0;
    }
    public Variable(int number){
        value = "";
        this.number = number;
    }
    public Variable(String value, int number){
        this.value = value;
        this.number = number;
    }
    @Override
    public int evaluate(int x) {
        return x;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return switch (value){
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(number);
    }
    @Override
    public String toString() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(value, variable.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value)*23;
    }
}
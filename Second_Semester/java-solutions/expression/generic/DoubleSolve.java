package expression.generic;

public class DoubleSolve implements GenericInterface<Double> {
    public Double add(Double x, Double y) {
        return x + y;
    }

    public Double subtract(Double x, Double y) {
        return x - y;
    }

    public Double multiply(Double x, Double y) {
        return x * y;
    }

    public Double divide(Double x, Double y) {
        return x / y;
    }

    public Double negate(Double x) {
        return -x;
    }

    public Double numberToGeneric(String x) {
        return Double.parseDouble(x);
    }
}

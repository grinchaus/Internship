package expression.generic;

import expression.exceptions.ArithmeticException;

public class GenericTabulator implements Tabulator {

    private <T> Object[][][] tabulateFor(GenericInterface<T> type, String expression,
                                         int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] mass = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        GenericExpressionParser<T> pars = new GenericExpressionParser<>();
        GenericGlobalExpression<T> result = pars.parse(expression, type);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        mass[i][j][k] = result.evaluate(
                                type.numberToGeneric(Integer.toString(x1 + i)),
                                type.numberToGeneric(Integer.toString(y1 + j)),
                                type.numberToGeneric(Integer.toString(z1 + k)));
                    } catch (ArithmeticException e) {
                        mass[i][j][k] = null;
                    }
                }
            }
        }

        return mass;
    }

    @Override
    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenericInterface<?> type;
        switch (mode) {
            case "i" -> type = new IntegerSolve();
            case "d" -> type = new DoubleSolve();
            case "u" -> type = new IntSolve();
            case "b" -> type = new ByteSolve();
            case "bi" -> type = new BigIntegerSolve();
            default -> throw new ArithmeticException("Unknown type");
        }
        return tabulateFor(type, expression, x1, x2, y1, y2, z1, z2);
    }
}

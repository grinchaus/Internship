package expression.generic;

public class Main {
    public static void main(String[] args) throws Exception {
        GenericTabulator mass = new GenericTabulator();
        Object[][][] res = mass.tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2);
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    System.out.println("x: " + i + " y: " + j + " z: " + k + " result: " + res[i][j][k]);
                }
            }
        }

    }
}

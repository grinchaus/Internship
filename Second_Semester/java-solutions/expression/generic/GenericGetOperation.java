package expression.generic;

public class GenericGetOperation {
    public static String getDigit(String expression, int pos) {
        int start = pos;
        if (expression.charAt(pos) == '-') {
            start++;
        }
        for (int i = start; i < expression.length(); i++) {
            if (!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '.') {
                return expression.substring(pos, i);
            }
            if (i == expression.length() - 1) {
                return expression.substring(pos, i + 1);
            }
        }
        return "";
    }
}

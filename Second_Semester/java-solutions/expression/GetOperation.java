package expression;

import expression.exceptions.ExpressionException;

public class GetOperation {
    public static String getDigit(String expression, int pos){
        int start = pos;
        if(expression.charAt(pos) == '-'){
            start++;
        }
        for(int i = start; i < expression.length(); i++){
            if (!Character.isDigit(expression.charAt(i))) {
                return expression.substring(pos, i);
            }
            if (i == expression.length() - 1) {
                return expression.substring(pos, i + 1);
            }
        }
        return "";
    }
    public static String getVariables(String expression, int pos) {
        StringBuilder str = new StringBuilder();
        char symbol = expression.charAt(pos);
        while (Character.isDigit(symbol)){
            str.append(symbol);
            pos++;
            if (pos == expression.length()){
                break;
            }
            symbol = expression.charAt(pos);
        }
        return str.toString();
    }
    public static int getIntOrOverflow(String con) throws ExpressionException {
        try{
            return Integer.parseInt(con);
        }
        catch (NumberFormatException e){
            throw new ExpressionException("Constant overflow: " + con);
        }
    }
}

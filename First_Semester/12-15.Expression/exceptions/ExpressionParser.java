package expression.exceptions;
import expression.*;
import java.util.*;
public class ExpressionParser implements TripleParser {
    private Stack<GlobalExpression> pars;
    private final Map<String,Integer> mapOperation = new HashMap<>(Map.of(
            "|",1,
            "^",2,
            "&",3,
            "+",4,
            "-",4,
            "*",5,
            "/",5,
            "~",6,
            "(",0));
    @Override
    public GlobalExpression parse(String expression) {
        mapOperation.put("t",6);
        mapOperation.put("l",6);
        Stack<String> operation = new Stack<>();
        pars = new Stack<>();
        boolean flagminus = true;
        for(int i = 0; i < expression.length(); i++){
            char simbol = expression.charAt(i);
            if(expression.length() == i + 1 && simbol == '-') throw new ArgumentExceptionClass("No argument");
            if ('x' == simbol || 'y' == simbol || 'z' == simbol) {
                pars.push(new Variable(String.valueOf(simbol)));
                flagminus = false;
            }else if(Character.isDigit(simbol) || simbol == '-' && Character.isDigit(expression.charAt(i+1)) && flagminus){
                String con = takeDigit(expression, i);
                if(Long.parseLong(con) > Integer.MAX_VALUE
                        || Long.parseLong(con) < Integer.MIN_VALUE) throw new ArgumentExceptionClass("Constant overflow");
                pars.push(new Const(Integer.parseInt(con)));
                i += con.length()-1;
                flagminus = false;
            }else if (simbol == '(') {
                operation.add("(");
                flagminus = true;
            }else if (simbol == ')') {
                if(flagminus) throw new ArgumentExceptionClass("An extra operator");
                while (!operation.peek().equals("(")) {
                    if (!operation.contains("(")) throw new ArgumentExceptionClass("Missing parenthesis");
                    pars.push(pushOper(operation.pop()));
                }
                operation.pop();
            }else {
                String oper = String.valueOf(simbol);
                if (mapOperation.containsKey(oper)) {
                    int noper = mapOperation.get(oper);
                    if ((oper.equals("l") || oper.equals("t")) && expression.charAt(i+1) == '0'){
                        noper = mapOperation.get(oper) + 1;
                        i++;
                    }
                    if (oper.equals("-") && flagminus){
                        oper = "~";
                        noper = mapOperation.get(oper) + 1;
                    }
                    while (!operation.empty() && noper <= mapOperation.get(operation.peek())) {
                        pars.push(pushOper(operation.pop()));
                    }
                    operation.add(oper);
                    flagminus = true;
                }else {
                    if(!Character.isWhitespace(simbol)) throw new ArgumentExceptionClass("Not the elements of the expression");
                }
            }
        }
        while(!operation.empty()){
            pars.push(pushOper(operation.pop()));
        }
        GlobalExpression buff = pars.pop();
        return buff;
    }
    private GlobalExpression pushOper(String oper){
        if (pars.empty()) throw new ArgumentExceptionClass("No first argument");
        GlobalExpression buffer2 = pars.pop();
        if(oper.equals("~")){
            return new CheckedNegate(buffer2);
        }
        if(oper.equals("l")){
          //  System.err.println("aboaobaob");
            return new HighBits(buffer2);
        }
        if(oper.equals("t")){
            //System.err.println("aboaobaob");
            return new LowBits(buffer2);
        }
        if (pars.empty()) throw new ArgumentExceptionClass("No second argument");
        GlobalExpression buffer1 = pars.pop();
        return switch (oper){
            case "|" -> new BinaryOr(buffer1,buffer2);
            case "^" -> new BinaryXor(buffer1,buffer2);
            case "&" -> new BinaryAnd(buffer1,buffer2);
            case "+" -> new CheckedAdd(buffer1,buffer2);
            case "-" -> new CheckedSubtract(buffer1,buffer2);
            case "*" -> new CheckedMultiply(buffer1,buffer2);
            case "/" -> new CheckedDivide(buffer1,buffer2);
            default -> throw new IllegalStateException("Unexpected value: " + oper);
        };
    }
    private String takeDigit(String expression, int pos){
        int nach = pos;
        if(expression.charAt(pos) == '-'){
            nach++;
        }
        for(int i = nach; i < expression.length(); i++){
            if (!Character.isDigit(expression.charAt(i))) {
                return expression.substring(pos, i);
            }
            if (i == expression.length() - 1) {
                return expression.substring(pos, i + 1);
            }
        }
        return null;
    }
}
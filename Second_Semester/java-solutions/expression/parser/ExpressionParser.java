package expression.parser;
import expression.*;
import expression.exceptions.TripleParser;

import java.util.*;
public class ExpressionParser implements TripleParser {
    private final Stack<String> operation =  new Stack<>();
    private final Stack<GlobalExpression> pars =  new Stack<>();
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
        boolean flagminus = true;
        GetOperation digit = new GetOperation();
        for(int i = 0; i < expression.length(); i++){
            char simbol = expression.charAt(i);
            if ('x' == simbol || 'y' == simbol || 'z' == simbol) {
                pars.push(new Variable(String.valueOf(simbol)));
                flagminus = false;
            }
            if(Character.isDigit(simbol) || simbol == '-' && Character.isDigit(expression.charAt(i+1)) && flagminus){
                String con = digit.getDigit(expression, i);
                pars.push(new Const(Integer.parseInt(con)));
                i += con.length()-1;
                flagminus = false;
            }else if (simbol == '(') {
                operation.add("(");
                flagminus = true;
            }else if (simbol == ')') {
                while (!operation.peek().equals("(")) {
                    pars.push(pushOper(operation.pop()));
                }
                operation.pop();
            }else {
                String oper = String.valueOf(simbol);
                if (mapOperation.containsKey(oper)) {
                    int noper = mapOperation.get(oper);
                    if (oper.equals("-") && flagminus) {
                        oper = "~";
                        noper = mapOperation.get(oper) + 1;
                    }
                    while (!operation.empty() && noper <= mapOperation.get(operation.peek())) {
                        pars.push(pushOper(operation.pop()));
                    }
                    operation.add(oper);
                    flagminus = true;
                }
            }
        }
        while(!operation.empty()){
            pars.push(pushOper(operation.pop()));
        }
        return pars.pop();
    }
    private GlobalExpression pushOper(String oper){
        GlobalExpression buffer = pars.pop();
        return switch (oper){
            case "|" -> new BinaryOr(pars.pop(),buffer);
            case "^" -> new BinaryXor(pars.pop(),buffer);
            case "&" -> new BinaryAnd(pars.pop(),buffer);
            case "+" -> new Add(pars.pop(),buffer);
            case "-" -> new Subtract(pars.pop(),buffer);
            case "*" -> new Multiply(pars.pop(),buffer);
            case "/" -> new Divide(pars.pop(),buffer);
            case "~" -> new UMinus(buffer);
            default -> throw new IllegalStateException("Unexpected value: " + oper);
        };
    }
}
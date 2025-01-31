package expression.generic;

import expression.exceptions.*;

import java.util.*;

public class GenericExpressionParser<T> {
    private Deque<GenericGlobalExpression<T>> pars;
    private final Map<String, Integer> mapOperation = Map.ofEntries(
            Map.entry("|", 10),
            Map.entry("^", 20),
            Map.entry("&", 30),
            Map.entry("+", 40),
            Map.entry("-", 40),
            Map.entry("*", 50),
            Map.entry("/", 50),
            Map.entry("~", 60),
            Map.entry("t", 60),
            Map.entry("l", 60),
            Map.entry("(", 0));

    private final List<String> listVariable = new LinkedList<>(List.of(
            "x",
            "y",
            "z"));

    public GenericGlobalExpression<T> parse(String expression, GenericInterface<T> type) throws ParserException {
        Deque<String> operation = new ArrayDeque<>();
        pars = new ArrayDeque<>();
        boolean flagMinus = true;
        if (expression.charAt(expression.length() - 1) == '-')
            throw new ArgumentException("No argument in" + expression);
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (listVariable.contains(Character.toString(symbol))) {
                pars.push(new GenericVariable<>(Character.toString(symbol)));
                flagMinus = false;
            } else if (Character.isDigit(symbol) || symbol == '-' && Character.isDigit(expression.charAt(i + 1)) && flagMinus) {
                String con = GenericGetOperation.getDigit(expression, i);
                T number = type.numberToGeneric(con);
                pars.push(new GenericConst<>(number));
                i += con.length() - 1;
                flagMinus = false;
            } else if (symbol == '(') {
                operation.push("(");
                flagMinus = true;
            } else if (symbol == ')') {
                if (flagMinus) throw new OperationException("An extra operator in pos " + i + 1);
                if (!operation.contains("("))
                    throw new ExpressionException("Missing parenthesis in " + i + 1);
                while (!Objects.equals(operation.peek(), "(")) {
                    pars.push(pushOperation(operation.pop(), type));
                }
                operation.pop();
            } else {
                String oper = String.valueOf(symbol);
                if (mapOperation.containsKey(oper)) {
                    int noper = mapOperation.get(oper);
                    if ((oper.equals("t") || oper.equals("l")) && expression.charAt(i + 1) == '0') {
                        if (i + 2 < expression.length() && !Character.isWhitespace(expression.charAt(i + 2))
                                && expression.charAt(i + 2) != '(')
                            throw new OperationException("Unknown operation in " + i + 1);
                        noper = mapOperation.get(oper) + 1;
                        i++;
                    }
                    if (oper.equals("-") && flagMinus) {
                        oper = "~";
                        noper = mapOperation.get(oper) + 1;
                    }
                    while (!operation.isEmpty() && noper <= mapOperation.get(operation.peek())) {
                        pars.push(pushOperation(operation.pop(), type));
                    }
                    operation.push(oper);
                    flagMinus = true;
                } else {
                    if (!Character.isWhitespace(symbol))
                        throw new ExpressionException("Not the elements of the expression " + '"' + symbol + '"' + " in pos " + i + 1);
                }
            }
        }
        while (!operation.isEmpty()) {
            pars.push(pushOperation(operation.pop(), type));
        }
        GenericGlobalExpression<T> empty = pars.pop();
        if (!pars.isEmpty()) throw new OperationException("No operation for Variables: " + empty);
        return empty;
    }

    private GenericGlobalExpression<T> pushOperation(String operation, GenericInterface<T> type) throws ParserException {
        if (pars.isEmpty()) throw new ArgumentException("No first argument");
        GenericGlobalExpression<T> buffer2 = pars.pop();
        if (operation.equals("~")) {
            return new GenericNegate<>(buffer2, type);
        }
        if (pars.isEmpty()) throw new ArgumentException("No second argument for " + operation);
        GenericGlobalExpression<T> buffer1 = pars.pop();
        return switch (operation) {
            case "+" -> new GenericAdd<>(buffer1, buffer2, type);
            case "-" -> new GenericSubtract<>(buffer1, buffer2, type);
            case "*" -> new GenericMultiply<>(buffer1, buffer2, type);
            case "/" -> new GenericDivide<>(buffer1, buffer2, type);
            default -> throw new OperationException("Unexpected operation: " + '"' + operation + '"');
        };
    }
}
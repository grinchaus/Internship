package expression.exceptions;

import expression.*;

import java.util.*;

public class ExpressionParser implements ListParser, TripleParser {
    private Deque<GlobalExpression> pars;
    private final StringBuilder var = new StringBuilder();
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

    @Override
    public GlobalExpression parse(String expression, final List<String> variables) throws ParserException {
        return doParse(variables, expression);
    }

    @Override
    public GlobalExpression parse(String expression) throws ParserException {
        return doParse(listVariable, expression);
    }

    private GlobalExpression doParse(List<String> listVariable, String expression) throws ParserException {
        Deque<String> operation = new ArrayDeque<>();
        pars = new ArrayDeque<>();
        boolean flagMinus = true;
        if (expression.charAt(expression.length() - 1) == '-')
            throw new ArgumentException("No argument in" + expression);
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '$') {
                var.setLength(0);
                var.append("$").append(GetOperation.getVariables(expression, i + 1));
                int index = listVariable.indexOf(var.toString());
                pars.push(new Variable(var.toString(), index));
                i += var.length() - 1;
                flagMinus = false;
            } else if (listVariable.contains(Character.toString(symbol))) {
                pars.push(new Variable(Character.toString(symbol)));
                flagMinus = false;
            } else if (Character.isDigit(symbol) || symbol == '-' && Character.isDigit(expression.charAt(i + 1)) && flagMinus) {
                String con = GetOperation.getDigit(expression, i);
                int number = GetOperation.getIntOrOverflow(con);
                pars.push(new Const(number));
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
                    pars.push(pushOperation(operation.pop()));
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
                        pars.push(pushOperation(operation.pop()));
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
            pars.push(pushOperation(operation.pop()));
        }
        GlobalExpression empty = pars.pop();
        if (!pars.isEmpty()) throw new OperationException("No operation for Variables: " + empty);
        return empty;
    }

    private GlobalExpression pushOperation(String operation) throws ParserException {
        if (pars.isEmpty()) throw new ArgumentException("No first argument");
        GlobalExpression buffer2 = pars.pop();
        switch (operation) {
            case "~":
                return new CheckedNegate(buffer2);
            case "l":
                return new HighBits(buffer2);
            case "t":
                return new LowBits(buffer2);
        }
        if (pars.isEmpty()) throw new ArgumentException("No second argument for " + operation);
        GlobalExpression buffer1 = pars.pop();
        return switch (operation) {
            case "|" -> new BinaryOr(buffer1, buffer2);
            case "^" -> new BinaryXor(buffer1, buffer2);
            case "&" -> new BinaryAnd(buffer1, buffer2);
            case "+" -> new CheckedAdd(buffer1, buffer2);
            case "-" -> new CheckedSubtract(buffer1, buffer2);
            case "*" -> new CheckedMultiply(buffer1, buffer2);
            case "/" -> new CheckedDivide(buffer1, buffer2);
            default -> throw new OperationException("Unexpected operation: " + '"' + operation + '"');
        };
    }
}
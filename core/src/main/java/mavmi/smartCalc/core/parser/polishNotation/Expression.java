package mavmi.smartCalc.core.parser.polishNotation;

import mavmi.smartCalc.core.parser.operator.OPERATOR;
import mavmi.smartCalc.core.parser.operator.OperatorException;
import mavmi.smartCalc.core.parser.polishNotation.node.Node;
import mavmi.smartCalc.core.parser.polishNotation.node.Number;
import mavmi.smartCalc.core.parser.polishNotation.node.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression {
    private final List<Node> parsedExpression = new ArrayList<>();

    private Expression() {

    }

    public Expression toPostfixPolishNotation() {
        Expression postfixExpression = new Expression();
        Stack<Node> stack = new Stack<>();

        for (Node node : parsedExpression) {
            if (node.isNumber()) {
                postfixExpression.parsedExpression.add(node);
            } else if (node.isOperator()) {
                Operator operator = (Operator) node;
                if (OPERATOR.isPrefixFunction(operator.getOperator())
                        || operator.getOperator().equals(OPERATOR.OP_PAREN)) {
                    stack.push(operator);
                } else if (operator.getOperator().equals(OPERATOR.CL_PAREN)) {
                    while (true) {
                        if (stack.empty()) {
                            throw new ExpressionException("Syntax error during conversion to postfix notation");
                        }
                        Operator peekOperator = (Operator) stack.pop();
                        if (peekOperator.getOperator().equals(OPERATOR.OP_PAREN)) {
                            break;
                        } else {
                            postfixExpression.parsedExpression.add(peekOperator);
                        }
                    }
                } else if (OPERATOR.isBinaryFunction(operator.getOperator())) {
                    while (!stack.empty()) {
                        Operator peekOperator = (Operator) stack.peek();
                        if (OPERATOR.isPrefixFunction(peekOperator.getOperator())
                                || peekOperator.getPriority() >= operator.getPriority()) {
                            postfixExpression.parsedExpression.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(operator);
                }
            }
        }

        while (!stack.empty()) {
            Node node = stack.pop();
            if (!node.isOperator()) {
                throw new ExpressionException("Invalid parentheses");
            }
            postfixExpression.parsedExpression.add(node);
        }

        return postfixExpression;
    }

    public static Expression fromString(String pureExpression) {
        int openedBrackets = 0;
        Expression expression = new Expression();
        String[] splittedException = pureExpression.split(" ");

        for (String line : splittedException) {
            for (int i = 0; i < line.length();) {
                char c = line.charAt(i);

                try {
                    String tmp;
                    if (Character.isDigit(c)) {
                        tmp = extractNumber(line, i);
                        expression.parsedExpression.add(new Number(Double.parseDouble(tmp)));
                    } else {
                        tmp = extractOperator(line, i);
                        OPERATOR operator = OPERATOR.fromString(tmp);
                        int priority = OPERATOR.getDefaultPriority(operator);

                        if (operator.equals(OPERATOR.OP_PAREN)) {
                            openedBrackets++;
                        } else if (operator.equals(OPERATOR.CL_PAREN)) {
                            openedBrackets--;
                        }

                        if (OPERATOR.isBinaryFunction(operator)) {
                            priority += OPERATOR.getDefaultPriority(OPERATOR.OP_PAREN) * openedBrackets;
                        }

                        expression.parsedExpression.add(new Operator(operator, priority));
                    }
                    i += tmp.length();
                } catch (Exception e) {
                    throw new ExpressionException(e.getMessage());
                }
            }
        }

        return expression;
    }

    private static String extractNumber(String str, int start) {
        StringBuilder result = new StringBuilder();

        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }
            result.append(str.charAt(i));
        }

        return result.toString();
    }

    private static String extractOperator(String str, int start) {
        StringBuilder result = new StringBuilder();

        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            result.append(c);

            try {
                OPERATOR.fromString(result.toString());
                break;
            } catch (OperatorException e) {
                if (i + 1 == str.length()) {
                    throw e;
                }
            }
        }

        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Node node : parsedExpression) {
            builder.append(node.toString());
        }

        return builder.toString();
    }
}

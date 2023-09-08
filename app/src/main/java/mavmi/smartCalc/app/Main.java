package mavmi.smartCalc.app;

import mavmi.smartCalc.core.parser.polishNotation.Expression;

public class Main {
    public static void main(String[] args) {
        String expressionStr = "3 + 4 * 2 /  (1 - 5) ^ 2 ";
        Expression expression = Expression.fromString(expressionStr);
        Expression postfixExpression = expression.toPostfixPolishNotation();
        System.out.println(postfixExpression);
    }
}

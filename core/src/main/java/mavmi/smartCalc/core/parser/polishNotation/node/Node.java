package mavmi.smartCalc.core.parser.polishNotation.node;

public abstract class Node {
    public boolean isNumber() {
        return this instanceof Number;
    }

    public boolean isOperator() {
        return this instanceof Operator;
    }
}

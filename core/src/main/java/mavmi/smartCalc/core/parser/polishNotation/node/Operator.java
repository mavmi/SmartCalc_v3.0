package mavmi.smartCalc.core.parser.polishNotation.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mavmi.smartCalc.core.parser.operator.OPERATOR;

@Getter
@Setter
@AllArgsConstructor
public class Operator extends Node {
    private OPERATOR operator;
    private int priority;

    @Override
    public String toString() {
        return OPERATOR.toString(operator);
    }
}

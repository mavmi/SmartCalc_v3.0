package mavmi.smartCalc.core.parser.polishNotation.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Number extends Node {
    private double number;

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

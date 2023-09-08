package mavmi.smartCalc.core.parser.polishNotation.node;

import mavmi.smartCalc.core.parser.operator.OPERATOR;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class OperatorTest {
    private static final String[] OPERATOR_NAMES = new String[]{
            "+",
            "-",
            "*",
            "/",
            "mod",
            "^",
            "(",
            ")",
            "cos",
            "sin",
            "tan",
            "acos",
            "asin",
            "atan",
            "sqrt",
            "ln",
            "log"
    };

    @Test
    public void initialization() {
        for (String line : OPERATOR_NAMES) {
            try {
                OPERATOR tmp = OPERATOR.fromString(line);
                Operator operator = new Operator(
                        tmp,
                        OPERATOR.getDefaultPriority(tmp)
                );
                assertEquals(operator.getOperator(), tmp);
                assertTrue(operator.isOperator());
                assertFalse(operator.isNumber());
            } catch (Exception e) {
                fail();
            }
        }
    }
}
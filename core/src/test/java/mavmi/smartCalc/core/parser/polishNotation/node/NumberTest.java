package mavmi.smartCalc.core.parser.polishNotation.node;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class NumberTest {
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
                double value = Math.random() * 123456;
                Number number = new Number(value);
                assertEquals(Double.compare(value, number.getNumber()), 0);
                assertTrue(number.isNumber());
                assertFalse(number.isOperator());
            } catch (Exception e) {
                fail();
            }
        }
    }
}
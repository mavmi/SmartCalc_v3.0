package mavmi.smartCalc.core.parser.operator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

class OPERATORTest {
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
    public void validString() {
        for (String line : OPERATOR_NAMES) {
            try {
                OPERATOR operator = OPERATOR.fromString(line);
                String string = OPERATOR.toString(operator);
                assertEquals(string, line);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "qweqwe",
            "afh3894hf",
            "qfuiojakf",
            "rjoqu3hfwehfkjh"
    })
    public void invalidString(String line) {
        assertThrows(OperatorException.class, () -> OPERATOR.fromString(line));
    }
}
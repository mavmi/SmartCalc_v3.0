package mavmi.smartCalc.core.parser.polishNotation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class ExpressionTest {
    @ParameterizedTest
    @CsvSource({
            "1+2*3/(4-5),1.02.03.0*4.05.0-/+",
            "4^(2*2)+12+1-1,4.02.02.0*^12.0+1.0+1.0-",
            "sin(2^3(4*14)),sin2.03.04.014.0*^",
            "sin2^3(4*13),sin2.03.04.013.0*^",
            "4^((2^2))+12+1-(1*(2*3)) mod 6,4.02.02.0^^12.0+1.0+1.02.03.0**mod6.0-"
    })
    public void infixToPostfix(String infix, String postfix) {
        try {
            Expression expression = Expression.fromString(infix);
            assertEquals(postfix, expression.toPostfixPolishNotation().toString());
        } catch (Exception e) {
            fail();
        }
    }
}
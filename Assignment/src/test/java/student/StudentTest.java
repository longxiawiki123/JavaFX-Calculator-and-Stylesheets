package student;

import calculator.CalculatorModel;
import calculator.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {
    @Test
    void answerCanBecomeTheNextLeftOperand() {
        CalculatorModel calculator = new CalculatorModel();
        calculator.inputDigit(8);
        calculator.selectOperation(Operation.ADD);
        calculator.inputDigit(5);
        calculator.evaluate();
        calculator.selectOperation(Operation.MULTIPLY);
        calculator.inputDigit(2);
        calculator.evaluate();
        assertEquals("26", calculator.getDisplay());
    }

    @ParameterizedTest
    @CsvSource({
        "999.9925, 1000.0075, 999999.9999",
        "999.995, 1000.005, Error"
    })
    void resultMustFitAfterRounding(String left, String right, String expected) {
        CalculatorModel calculator = new CalculatorModel();
        enterNumber(calculator, left);
        calculator.selectOperation(Operation.MULTIPLY);
        enterNumber(calculator, right);
        calculator.evaluate();

        assertEquals(expected, calculator.getDisplay());
    }

    private static void enterNumber(CalculatorModel calculator, String number) {
        for (char character : number.toCharArray()) {
            if (character == '.') {
                calculator.inputDecimalPoint();
            } else {
                calculator.inputDigit(character - '0');
            }
        }
    }
}

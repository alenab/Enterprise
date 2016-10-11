package ua.goit.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CheckOperationsTest {

    private Calculator calculator = new Calculator();

    @Test
    public void checkPlusOperationOfInteger() {
        Assert.assertEquals("4", calculator.calculate("+ 2 2"));
    }

    @Test
    public void checkMinusOperationOfInteger() {
        Assert.assertEquals("2", calculator.calculate("- 4 2"));
    }

    @Test
    public void checkPlusOperationOfDouble() {
        double number1 = Float.MAX_VALUE + 2E36d;
        double number2 = 2E20d;
        Assert.assertEquals(String.valueOf(number1 + number2),
                calculator.calculate(String.format("+ %s %s", String.valueOf(number1), String.valueOf(number2))));
    }

    @Test
    public void checkMinusOperationOfDouble() {
        double number1 = Float.MIN_VALUE - 2E36d;
        double number2 = 2E20d;
        Assert.assertEquals(String.valueOf(number1 - number2),
                calculator.calculate(String.format("- %s %s", String.valueOf(number1), String.valueOf(number2))));
    }

    @Test
    public void checkPlusOperationOfLong() {
        long number1 = Integer.MAX_VALUE + 2L;
        long number2 = 44L;
        Assert.assertEquals(String.valueOf(number1 + number2),
                calculator.calculate(String.format("+ %s %s", String.valueOf(number1), String.valueOf(number2))));
    }

    @Test
    public void checkMinusOperationOfLong() {
        long number1 = Integer.MAX_VALUE + 10L;
        long number2 = 8L;
        Assert.assertEquals(String.valueOf(number1 - number2),
                calculator.calculate(String.format("- %s %s", String.valueOf(number1), String.valueOf(number2))));
    }

    @Test
    public void checkPlusOperationOfFloat() {
        Assert.assertEquals(String.valueOf(1.2f + 1.1f), calculator.calculate("+ 1.2 1.1"));
    }

    @Test
    public void checkMinusOperationOfFloat() {
        Assert.assertEquals(String.valueOf(0.2f - 1.8f), calculator.calculate("- 0.2 1.8"));
    }

    @Test
    public void checkSqrtOperation() {
        Assert.assertEquals(String.valueOf(Math.sqrt(4)), calculator.calculate("sqrt 4"));
    }

    @Test
    public void checkSqrtOperationExceptionWithTwoNumbers() {
        try {
            calculator.calculate("sqrt 4 4");
            Assert.fail("An attempt to enter " + " and one number there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkOperationWithEmptyString() {
        try {
            calculator.calculate("");
            Assert.fail("An attempt to enter empty string there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkOperationPlusWithOneNumber() {
        try {
            calculator.calculate("+ 2");
            Assert.fail("An attempt to enter " + " and one number there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkIncorrectOperationSign() {
        try {
            calculator.calculate("f 2 2");
            Assert.fail("An attempt to enter incorrect operation there are no exception");
        } catch (IllegalStateException e) {

        }
    }

    @Test(expected = IllegalStateException.class)
    public void checkDataMinusOperationWithInvalidInput() {
        calculator.calculate("- M R");
    }

    @Test
    public void checkSumThreeNumbers() {
        Assert.assertEquals("9", calculator.calculate("+ 3 3 3"));
    }
}

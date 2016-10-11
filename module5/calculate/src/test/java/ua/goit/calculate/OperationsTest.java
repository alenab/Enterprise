package ua.goit.calculate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.goit.calculator.Calculator;
import ua.goit.calculator.operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationsTest {

    private Calculator calculator = new Calculator();

    @Before
    public void before() {
        List<Operation> list = new ArrayList<>();
        list.add(new DataMinusOperation());
        list.add(new DoubleDivideOperation());
        list.add(new DoubleMultiplyOperation());
        calculator.setOperations(list);
        calculator.init();
    }

    @Test
    public void checkDivideOperation() {
        Assert.assertEquals(String.valueOf(4d - 2d), calculator.calculate("/ 4 2"));
    }

    @Test
    public void checkDivideOperationWithOneNumber() {
        try {
            calculator.calculate("/ 4");
            Assert.fail("An attempt to enter / and one number there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkMultiplyOperation() {
        Assert.assertEquals(String.valueOf(2d * 2d), calculator.calculate("* 2 2"));
    }

    @Test
    public void checkDataMinusOperation() {
        Assert.assertEquals("5 days", calculator.calculate("- 20.01.2010 15.01.2010"));
    }

    @Test
    public void checkDataMinusOperationValidationInvalidValue() {
        DataMinusOperation operation = new DataMinusOperation();
        Assert.assertFalse(operation.validateInput("zuzu"));
    }
}
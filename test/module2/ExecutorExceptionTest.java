package module2;

import org.junit.Assert;
import org.junit.Test;

public class ExecutorExceptionTest {

    @Test
    public void checkExceptionWhileAddingTask() {
        Executor<Integer> testExecutor = new ExecutorImpl<>();
        IntegerTask intTask = new IntegerTask(2);
        testExecutor.addTask(intTask);
        testExecutor.execute();
        try {
            testExecutor.addTask(intTask);
            Assert.fail("An attempt to add executed Task there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkExceptionWhileAddingTaskWithValidator() {
        Executor<Number> testExecutor = new ExecutorImpl<>();
        testExecutor.addTask(new LongTask(2L), new NumberValidator());
        testExecutor.execute();
        try {
            testExecutor.addTask(new LongTask(2L), new NumberValidator());
            Assert.fail("An attempt to add executed Task (with validator) there are no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkExceptionInGetValidResults() {
        Executor<Number> testExecutor = new ExecutorImpl<>();
        testExecutor.addTask(new IntegerTask(10));
        testExecutor.addTask(new LongTask(10L));
        try {
            testExecutor.getValidResults();
            Assert.fail("An attempt to get valid results in not executed task there is no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkExceptionInGetInvalidResults() {
        Executor<Number> testExecutor = new ExecutorImpl<>();
        testExecutor.addTask(new IntegerTask(10));
        testExecutor.addTask(new LongTask(10L), new NumberValidator());
        try {
            testExecutor.getInvalidResults();
            Assert.fail("An attempt to get invalid results in not executed task there is no exception");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void checkAddingTaskNull() {
        Executor<Integer> testExecutor = new ExecutorImpl<>();
        testExecutor.addTask(null);
        try {
            testExecutor.execute();
        } catch (NullPointerException e) {
            Assert.fail("An attempt to add null task leads to exception during execution");
        }
    }

    @Test
    public void checkLongTaskGetResultWithoutExecute() {
        LongTask longTask = new LongTask(10L);
        Assert.assertEquals("", null, longTask.getResult());
    }

    @Test
    public void checkNullTaskIsNotPresentInResults() {
        Executor<Long> testExecutor = new ExecutorImpl<>();
        testExecutor.addTask(null);
        testExecutor.execute();
        Assert.assertEquals("", 0, testExecutor.getValidResults().size());
        Assert.assertEquals("", 0, testExecutor.getInvalidResults().size());
    }
}

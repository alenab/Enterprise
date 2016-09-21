import java.util.List;

public class Example {

    public void test(List<Task<Integer>> intTasks) {
        Executor<Number> numberExecutor = new ExecutorImpl<>();

        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new LongTask(10L), new NumberValidator());

        numberExecutor.execute();

        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        Executor<Integer> taskExecutor = new ExecutorImpl<>();
        IntegerTask intTask = new IntegerTask(1);
        taskExecutor.addTask(intTask);
        taskExecutor.execute();
        List<Integer> validResults = taskExecutor.getValidResults();
        System.out.println(validResults);
    }
}
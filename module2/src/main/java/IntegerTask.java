public class IntegerTask implements Task<Integer> {
    Integer n;

    IntegerTask(Integer number) {
        n = number;
    }

    @Override
    public void execute() {
        n += 2;
    }

    @Override
    public Integer getResult() {
        return n;
    }
}

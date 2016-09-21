package module2;

public class LongTask implements Task<Long> {
    Long l;
    Long result;

    public LongTask(Long number){
        l = number;
    }

    @Override
    public void execute() {
        if (l % 2 != 0) {
            result = l;
        }
    }

    @Override
    public Long getResult() {
     return result;
    }
}

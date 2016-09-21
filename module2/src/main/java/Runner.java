import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Task<Integer>> list = new ArrayList<>();
        list.add(new IntegerTask(4));
        list.add(new IntegerTask(3));
        list.add(new IntegerTask(8));
        Example example = new Example();
        example.test(list);
    }
}

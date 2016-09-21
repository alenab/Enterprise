package module3.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumExecutor {

    public static void main(String[] args) {
        getSquareSum(new int[]{1, 2, 3, 4, 5, 6, 1, 2, 3, 2, 1}, 3);
    }

    public static long getSquareSum(int[] values, int numberOfThreads) {
        List<Future<Long>> partSums = new ArrayList<>();
        ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads);
        ArrayDivider divider = new ArrayDivider(values, numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            int[] partArray = divider.divide(i);
            Future<Long> f = executors.submit(new Callable<Long>() {
                @Override
                public Long call() {
                    phaser.register();
                    long partSum = 0;
                    for (int element : partArray) {
                        partSum += element * element;
                    }
                    phaser.arriveAndDeregister();
                    return partSum;

                }
            });
            partSums.add(f);
        }
        phaser.awaitAdvance(numberOfThreads);
        executors.shutdown();
        long sum = 0;
        for (Future<Long> partSum : partSums) {
            try {
                System.out.println(partSum.get());
                sum += partSum.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Final sum :" + sum);
        return sum;
    }
}

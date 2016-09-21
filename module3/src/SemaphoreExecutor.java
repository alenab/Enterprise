public class SemaphoreExecutor implements Semaphore {

    private final int numberOfThread;
    private volatile int counter;

    private final Object lock = new Object();

    public SemaphoreExecutor(int number) {
        numberOfThread = number;
        counter = numberOfThread;
    }

    @Override
    public void acquire() {
        acquire(1);
    }

    @Override
    public void acquire(int permits) {
        if (permits > numberOfThread) {
            throw new IllegalStateException("Quantity of threads more than target");
        }
        synchronized (lock) {
            while (getAvailablePermits() <= permits) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter -= permits;
        }

    }


    @Override
    public void release() {
        release(1);
    }

    @Override
    public void release(int permits) {
        if (permits > numberOfThread || permits > (numberOfThread - getAvailablePermits())) {
            throw new IllegalStateException("Quantity of threads more than target");
        }
        synchronized (lock) {
            counter += permits;
            lock.notifyAll();
        }
    }

    @Override
    public synchronized int getAvailablePermits() {
        return counter;
    }
}

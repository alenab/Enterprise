public class UsageExample {

    public static Thread getNewWorkerThread(int index, Semaphore semaphore) {
        return new Thread(String.format("worker-%d", index)) {
            @Override
            public void run() {
                semaphore.acquire();

                for (int i = 0; i < 10; i++) {
                    System.out.printf("%s: %d (Av: %d)\n", getName(), i, semaphore.getAvailablePermits());
                    try {
                        sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                semaphore.release();
            }
        };
    }

    public static void main(String[] args) {
        Semaphore semaphore = new SemaphoreExecutor(5);

        new Thread("our_main") {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    Thread thread = getNewWorkerThread(i, semaphore);
                    thread.start();

                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i == 2) {
                        System.out.println("Reserved space...");
                        semaphore.acquire(4);
                        System.out.printf("%s: working (Av: %d)\n", getName(), semaphore.getAvailablePermits());
                    }

                    if (i == 9) {
                        System.out.println("Released space...");
                        semaphore.release(4);
                        System.out.printf("%s: working (Av: %d)\n", getName(), semaphore.getAvailablePermits());
                    }
                }
            }
        }.start();

    }
}

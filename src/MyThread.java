public class MyThread implements Runnable {
    private boolean isActive;

    void disable() {
        isActive = false;
    }

    MyThread() {
        isActive = true;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s is started...\n", name);
        int counter = 0;
        while (isActive) {
            System.out.printf("Circle #%d...\n", counter++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.printf("Thread %d is interrupted...\n", name);
            }


        }

        System.out.printf("Thread %s is Finished...\n", name);
    }

}

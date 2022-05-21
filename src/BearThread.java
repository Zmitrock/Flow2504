public class BearThread extends Thread {
    Object locker;

    BearThread(Object locker) {
        this.locker = locker;
    }

    @Override
    public void run() {
        while (true) {
            int i = 1;
            try {
                synchronized (locker) {
                    locker.wait(1000);        //останавливаем поток
                    System.out.println("Медведь проснулся!");
                    while (HWThreads0305Main.pot > 0) {
                        synchronized (locker) {
                            HWThreads0305Main.pot -= 1;
                        }
                        System.out.printf("Медведь съел %d порцию(й) меда\n", i);
                        i++;
                    }
                    System.out.println("Медведь съел весь мед!\n" +
                            "Медведь уснул");
                    locker.notifyAll();// передает сигнал всем потокам
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }

    }
}
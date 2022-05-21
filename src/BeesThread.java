public class BeesThread extends Thread {
    Object locker;

    BeesThread(Object locker) {
        this.locker = locker;
    }

    @Override
    public void run() {
        while (true) {

            for (int N = 1; N < 10001; N++) { // N=10000 - количество пчел
                synchronized (locker) {
                    HWThreads0305Main.pot++;
                }
                System.out.printf("Пчела №%d принесла порцию меда\n", N);
                System.out.printf("В горшочке %d порций меда\n", HWThreads0305Main.pot);
                if (HWThreads0305Main.pot == 1000) {// если горшок полон
                    System.out.println("Горшочек полон");
                    try {
                        synchronized (locker) {
                            locker.notifyAll();// передает сигнал всем потокам
                            locker.wait(1000); // останавливаем поток пчел
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (Thread.currentThread().isInterrupted()){
                return;
            }
        }
    }

}
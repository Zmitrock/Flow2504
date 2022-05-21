public class Service2 implements Runnable {
    Object locker;

    Service2(Object locker) {
        this.locker = locker;
    }

    @Override
    public void run() {

//        try {
//            locker.wait(5000); //ждем 5сек
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (true) {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Service 2 выполняется" + i);
                if (i % 10 == 0) {
                    try {
//                        this.notify(); // посылвает сигнал потоку, который вызвал наш поток
                        synchronized (locker) {
                            locker.wait(1000);        //останавливаем поток через каждые 10 итераций
                            locker.notifyAll();// передает сигнал всем потокам
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


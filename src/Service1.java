public class Service1 implements Runnable{
    Object locker;
    Service1 (Object locker){
        this.locker = locker;
    }

    @Override
    public void run() {
        while (true){
            for (int i = 0; i <1000; i++) {
                System.out.println("Service 1 выполняется"+i);
                if (i%10==0){
                    try {
//                        this.notify(); // посылвает сигнал потоку, который вызвал наш поток
                        synchronized (locker){
                            locker.wait();        //останавливаем поток через каждые 10 итераций
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

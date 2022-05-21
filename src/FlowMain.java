import java.util.concurrent.*;

public class FlowMain {
    public static Integer count = 1000000;//к примеру. общая переменная для нескольких потоков
    public static Object locker = new Object();// объект для блокировки потоков исинхрнизации, любого ссылочного типа

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); //сначала поток 1, затем поток 2 выполняется
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
//executorService.shutdown(); //завершить пул потоков
        executorService.execute(new Service1((locker)));
        executorService.execute(new Service2(locker));

        ThreadPoolExecutor task = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        Future<Integer> result = task.submit(new Apple());
//        result.isDone(); // вернет тру если поток выполнился
        while (true) {
            if (result.isDone()) {
                task.shutdown();
                System.out.println(result.get());
                return;
            } else {
                Thread.sleep(10000);
            }


        }


//        Service1 service1 = new Service1(locker);
//        Thread thread1 = new Thread((service1));// вариант 1 создания потока
//        Service2 service2 = new Service2(locker);
//        Thread thread2 = new Thread((service2));// вариант 1 создания потока

//        Thread thread2 = new Thread(() -> {      // вариант 2 через лямбда
//            while (true) {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("Service 2 выполняется" + i);
//                }
//            }
//        });

//        thread1.start();
//        thread2.start();

//        MoneyThread moneyThread1 = new MoneyThread();//900
//        MoneyThread2 moneyThread2 = new MoneyThread2();//800
//        moneyThread1.start();
//        moneyThread2.start();
//        System.out.println("result = "+count);
//        try {
//            moneyThread1.join();
//            moneyThread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("result = "+count);


    }
}

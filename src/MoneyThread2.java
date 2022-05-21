public class MoneyThread2 extends Thread {


    MoneyThread2() {// передаем переменную из мэйна, общую для нескольких потоков

    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (FlowMain.locker) {
                FlowMain.count -= 1;
            }
            System.out.println("sum2 = " + FlowMain.count);
        }

    }
}

public class MoneyThread extends Thread {

    MoneyThread() {// передаем переменную из мэйна, общую для нескольких потоков

    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (FlowMain.locker) {
                FlowMain.count -= 1; //   объект, кот нужно синхронизировать
            }
            System.out.println("sum = " + FlowMain.count);
        }

    }
}

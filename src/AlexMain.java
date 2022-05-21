public class AlexMain {
           public static void main(String[] args) throws InterruptedException {
            String name = Thread.currentThread().getName();
            System.out.printf("Thread %s is started...\n", name);
//            MyThread mth = new MyThread();
//            Thread th = new Thread(mth, "My Thread");
//            th.start();
//            Thread.sleep(4000);
//            mth.disable();
//************часть 2
               Resource resource = new Resource();
               for (int i =0; i<5; i++) {
                   Thread th = new Thread(new ResourceUser(resource) );
                   th.setName("Thread #" +i);
                   th.start();
               }








            System.out.printf("Thread %s is Finished...\n", name);
        }
    }




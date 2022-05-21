import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchCollect {
    public static void main(String[] args) throws InterruptedException, IOException {
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> synchList = Collections.synchronizedList(list);

        int y = 1000;
        AtomicInteger y2 = new AtomicInteger(1000);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                y2.decrementAndGet();
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.awaitTermination(2, TimeUnit.SECONDS);
        executorService.shutdown();//останавливаем потоки
        System.out.println(y2.get());

        String path = System.getProperty("user.dir") + File.pathSeparator + "newFile.txt"; //адрес текущего проекта с разделителем
//        File file1 = new File("D:\\newFile.txt");
        File file1 = new File(path);
        if (!file1.exists()) {
            try {
                boolean result = file1.createNewFile();    //создать новый файл D:\newFile.txt,  возвращает булеан
                System.out.println(result ? "Файл создан" : "Файл не создан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileOutputStream outputStream2 = null;
        try {
            inputStream = new FileInputStream(file1);
            byte[] mass = new byte[inputStream.available()];
            inputStream.read(mass);

            System.out.println(new String(mass, "UTF-8"));


            outputStream = new FileOutputStream(new File(System.getProperty("user.dir") +
                    File.pathSeparator + "newFile2.txt"));
            outputStream.write(mass); // записываем данные из файла в файл

        String path2 = System.getProperty("user.dir") +
                File.separator + "testFolder";
        File file2 = new File(path2);
        file2.mkdir();

            outputStream2=new FileOutputStream(new File(path2+File.separator+"newFile2.txt"));
            outputStream2.write(mass);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream2.close();// повесил сигнатуру, поэтому без трай кэч
        }


    }

}

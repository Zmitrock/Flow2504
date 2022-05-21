import java.io.*;
import java.nio.charset.StandardCharsets;

public class Class1105 {
    public static void main(String[] args) throws IOException {
        String text = "Тестовый текст";
        File file = new File("file1.txt");

        try (FileOutputStream fos = new FileOutputStream(file); BufferedOutputStream bufOutStr = new BufferedOutputStream(fos)) {
            bufOutStr.write(text.getBytes(), 0, text.getBytes().length);
            System.out.println("записали в файл 1 строку \"Тестовый текст\"");
//            bufOutStr.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bufInputStr = new BufferedInputStream(fis)) {
            byte[] mass = new byte[bufInputStr.available()];
            bufInputStr.read(mass);
            System.out.print("Читаем из файла1 следующие данные: ");
            System.out.println(new String(mass));
//            int b;
//            while ((b = bufInputStr.read()) != -1){
//                System.out.println((char) b);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student st1 = new Student("Иванов", "MA", 8.0);
        Student st2 = new Student("Сидоров", "MA", 9.0);

        try (FileOutputStream fos1 = new FileOutputStream("file2.txt"); ObjectOutputStream objOutStr = new ObjectOutputStream(fos1)) {
            objOutStr.writeObject(st1);
            System.out.println("записали в файл2 объект st1 класса \"Student\"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("EOFException");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis1 = new FileInputStream("file2.txt"); ObjectInputStream objIntStr = new ObjectInputStream(fis1)) {
            Student result = (Student) objIntStr.readObject();
//            System.out.println("результат " + result.getFio() + " " + result.getGroup());
            System.out.print("Считываем из файла 2 записанный объект: ");
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String text2 = "Строка для записи файла";
        System.out.println("Исходная строка: " + text2);
        try (FileWriter writer = new FileWriter("file3.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(text2);
            System.out.println("записали исходную строку в файл 3 через BufferedWriter");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("file3.txt");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {
//            char[] result = new char[fis.available()];//вариант считывания с ошибкой длины массива, пустые ячейки добавляются
//            isr.read(result);
//            System.out.println(new String(result));
            System.out.println("Считываем данные из файла 3 через InputStreamReader: ");
            int b;
            while ((b = isr.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder res = new StringBuilder();

        try (FileReader reader = new FileReader("file3.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.append(line);
            }
            bufferedReader.readLine();

            System.out.println("\nСчитываем из файла 3 через BufferedReader: " + res.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //********
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к файлу");
        String path = reader1.readLine();
        String word = "файл";//слово для поиска
        StringBuilder res1 = new StringBuilder();
        try (FileReader reader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res1.append(line);
            }
            int count = 0;
            int x;
            while ((x = res1.indexOf(word)) >= 0) {

                res1.delete(x, x + word.length());
                count++;
            }
            System.out.printf("Слово \"%s\" встречается в файле %d раз", word, count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
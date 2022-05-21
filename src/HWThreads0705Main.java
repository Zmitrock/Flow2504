
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HWThreads0705Main {
	public static void main(String[] args) throws IOException {
//		ЗАДАНИЕ 1. Пользователь с клавиатуры вводит путь к файлу. После чего
//		содержимое файла отображается на экране.
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Введите путь к файлу с указанием названия файла с расширением");
		String filePath =reader.readLine();
		System.out.println(filePath);//проверка введенного пути
		FileInputStream inputStream0 = new FileInputStream(filePath);
		byte[] mass0 = new byte[inputStream0.available()];
		inputStream0.read(mass0);
		System.out.println("содержимое файла: "+new String (mass0));

		inputStream0.close(); // закрываем поток ввода

//		ЗАДАНИЕ 2. Напиши программу в которой.:
//			1. Создайте файл “1.txt” в корне проекта и запишите с помощью потока
//			вывода фразу “Миша ездит на Жигули, а Катя на Мерседес”. (Если пока
//			сложно, можно после создания файла записать фразу руками)
//			2. Создайте в папке с проектом папку “Результат”.
//			3. Создайте в папке Результат файл “2.txt”.
//			4. Откройте два потока для ввода и вывода данных. Поток для ввода
//			читает информацию из файла “1.txt” массив байт.
//			5. Преобразуйте массив байт в тип String, замените “Жигули” на “BMW”, а “Мерседес” на “Рено”.
//			6. Запишите результат п.4 в файл “2.txt”.
		String path = System.getProperty("user.dir") + File.separator + "1.txt";// путь к файлу “1.txt” в корне проекта
		File file1 = new File(path);
		try {
			file1.createNewFile();// создаем файл
		} catch (IOException e) {
			e.printStackTrace();
		}
		String phrase = "Миша ездит на Жигули, а Катя на Мерседес";
		byte[] mass = null;
		FileOutputStream outputStream = null;

		try {
			mass = phrase.getBytes();// преобразовываем строку в массив байт
			outputStream = new FileOutputStream(file1);
			try {
				outputStream.write(mass);
			} catch (IOException e) {
				e.printStackTrace();
			}
			outputStream.close(); // закрываем поток вывода
			System.out.println(new String(mass));// строка для проверки, необязательная
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathFolder = System.getProperty("user.dir") + File.separator + "Результат";
		File folderRes = new File(pathFolder);
		folderRes.mkdir();
		File file2 = new File(pathFolder + File.separator + "2.txt");
		try {
			file2.createNewFile();// создаем файл
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream inputStream = null;
		FileOutputStream outputStream2 = null;

		try {
			inputStream = new FileInputStream(file1);
			byte[] mass2 = new byte[inputStream.available()];
			inputStream.read(mass2);
			String string1 = new String(mass2);
//			5. Преобразуйте массив байт в тип String, замените “Жигули” на “BMW”,
//			а “Мерседес” на “Рено”.
			StringBuilder sb = new StringBuilder(string1);

			sb.replace(sb.indexOf("Жигули"), sb.indexOf("Жигули")+6, "BMW");
			sb.replace(sb.indexOf("Мерседес"), sb.indexOf("Мерседес")+8, "Рено");

			System.out.println(sb.toString());// проверочная строка
			byte [] massRes = sb.toString().getBytes();

			outputStream2 = new FileOutputStream(file2);
			outputStream2.write(massRes);
			inputStream.close(); // закрываем поток ввода
			outputStream2.close(); // закрываем поток вывода

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

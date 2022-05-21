
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutput {
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir") + File.pathSeparator + "newFile.txt";
//		File file0 = new File("D:\\newFile.txt");
//		try {
//			file0.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		File file1 = new File(path);
		if (!file1.exists()) {
			try {
				boolean result = file1.createNewFile();
				System.out.println(result ? "Файл создан" : "Файл не создан");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

			outputStream = new FileOutputStream(
					new File(System.getProperty("user.dir") + File.pathSeparator + "newFileOutput.txt"));
			outputStream.write(mass);
			System.out.println(new String(mass));

			String path2 = System.getProperty("user.dir") + File.separator + "testFolderNew";
			File file2 = new File(path2);
			file2.mkdir();
			outputStream2 = new FileOutputStream(new File(path2 + File.separator + "newFileOutput2.txt"));

			outputStream2.write(mass);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				outputStream2.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


		}
		outputStream2.close();

//		File file3 = new File("D:\\eclipse-workspace" + File.separator + "taskInput.txt");
//		FileInputStream inputStream3 = new FileInputStream(file3);
//		byte[] mass2 = new byte[inputStream3.available()];
//		inputStream3.read(mass2);
//		System.out.println(new String(mass2));
//		inputStream3.close();
	}

}

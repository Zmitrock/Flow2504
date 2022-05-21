
public class HWThreads0305Main {
	public static Integer pot =0; //начальное состояние - горшок пустой, горшок полон pot =1000;
	public static Object locker = new Object();
	public static void main(String[] args) throws InterruptedException {
		// Задача. Есть N пчел и медведь. Они пользуются одним горшком меда, вмещающим H
		// порций меда. Сначала горшок пустой. Пока горшок не наполнится, медведь спит,
		// потом съедает весь меди засыпает. Каждая пчела многократно собираетпо одной порции меда и
		// кладет ее в горшок. Пчела, которая приносит последнюю порцию меда и заполняет горшок, будит
		// медведя. Представьте медведя и пчел процессами, разработайте код, моделирующий их действия.

		BeesThread beesThread = new BeesThread(locker);
		BearThread bearThread = new BearThread(locker);

		beesThread.start();
		bearThread.start();



//		beesThread.join();
//		bearThread.join();
bearThread.interrupt();
beesThread.interrupt();
	}

}

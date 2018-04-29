package concurrency;

import fn.ThreadUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	static Random r = new Random();

	@Test
	public void test1() throws InterruptedException {

		List<Thread> list = new ArrayList<>();
			Semaphore semaphore = new Semaphore(10);
			for (int i = 0; i < 10; i++) {
				int j = r.nextInt(5) + 1;
				System.out.println(j);
				MyThread thread = new MyThread(semaphore, j);
				list.add(thread);
			}
		for (Thread thread : list) {
			thread.start();
		}
		for (Thread thread : list) {
			thread.join();
		}

	}

	private static class MyThread extends Thread {
		private Semaphore semaphore;
		private int count;

		public MyThread(Semaphore semaphore, int count) {
			this.semaphore = semaphore;
			this.count = count;
		}

		@Override
		public void run() {
			try {
				ThreadUtil.println("get"+semaphore.availablePermits());
				semaphore.acquire(count);
				ThreadUtil.sleep(r.nextInt(9000));

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release(count);
				ThreadUtil.println("continue"+semaphore.availablePermits());
			}
		}
	}
}

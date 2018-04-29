package concurrency;

import fn.ThreadUtil;

import java.util.concurrent.CountDownLatch;

public class Driver {
	private static final int N = 3;

	public static void main(String args[]) throws InterruptedException {
		CountDownLatch major = new CountDownLatch(1);
		CountDownLatch childes = new CountDownLatch(N);

		for (int i = 0; i < N; ++i) // create and start threads
			new Thread(new Worker(major, childes)).start();

		ThreadUtil.println("start");
		major.countDown();      // let all threads proceed
		ThreadUtil.println(" childes continue");
		childes.await();           // wait for all to finish
	}

}

class Worker implements Runnable {
	private final CountDownLatch major;
	private final CountDownLatch childes;

	Worker(CountDownLatch major, CountDownLatch childes) {
		this.major = major;
		this.childes = childes;
	}

	public void run() {
		try {
			ThreadUtil.println("start");
			major.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {

		doWork();
		childes.countDown();
		}
	}

	void doWork() {
		ThreadUtil.println();
	}
}
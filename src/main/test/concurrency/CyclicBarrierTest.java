package concurrency;

import fn.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	private static CyclicBarrier lock = new CyclicBarrier(5);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int j = i;
		new Thread(()->{
			try {
				if(j==3){
					lock.reset();
				}
				ThreadUtil.println(" wait"+lock.getNumberWaiting()+lock.getParties());
				System.out.println(lock.await());
				ThreadUtil.println(" wait"+lock.getNumberWaiting()+lock.getParties());
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
		}).start();
		ThreadUtil.sleep();
		}

		ThreadUtil.println(" end");
	}
}

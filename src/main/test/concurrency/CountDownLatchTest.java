package concurrency;

import fn.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	private static CountDownLatch lock = new CountDownLatch(0);

	public static void main(String[] args) throws InterruptedException {
		ThreadUtil.print();
		System.out.println("start");
		lock.await();
		System.out.println("continue");
	}

	@Test
	public void test1(){
		String s = "HelloWorldMyNameIsCarl".replaceAll("(.)([A-Z])", "$1_$2");
		System.out.println(s);

	}
}


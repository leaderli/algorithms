package guava;

import junit.framework.TestCase;

import java.util.Optional;
import java.util.concurrent.locks.LockSupport;

public class TestOptional extends TestCase {

	public void test1() throws InterruptedException {

		Optional<Integer> integer = Optional.ofNullable(2);
		System.out.println(integer.orElse(1));
		synchronized (integer) {

			while (true) {
				integer.wait(100);
				LockSupport.parkNanos(1000_000__00);
				Thread.sleep(100);
			}
		}
	}

}

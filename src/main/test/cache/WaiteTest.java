package cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaiteTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread = new Thread() {
            @Override
            public void run() {
                setName("notify");
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + "---" + "wakeup");
                    notifyAll();
                }
            }
        };

        synchronized (thread) {

            System.out.println(Thread.currentThread().getName() + "---" + "start");
            thread.start();
            System.out.println(Thread.currentThread().getName() + "---" + "block");
            thread.wait();
            System.out.println(Thread.currentThread().getName() + "---" + "contine");
        }
    }
}

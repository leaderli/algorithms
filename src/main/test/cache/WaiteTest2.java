package cache;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaiteTest2 {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-start");
                System.out.println(((ReentrantLock)lock).isHeldByCurrentThread());
                condition.signalAll();
                System.out.println(((ReentrantLock)lock).isHeldByCurrentThread());
                lock.unlock();
                System.out.println(((ReentrantLock)lock).isHeldByCurrentThread());
            }
        };
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-start");
            thread.start();
            System.out.println(Thread.currentThread().getName() + "-block");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "-continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

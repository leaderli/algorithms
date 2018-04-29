package cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        boolean flag = false;
        while (!owner.compareAndSet(null, current)){

        }
    }

    public void unLock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                lock.unLock();
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

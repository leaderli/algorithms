package cache;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MyCLHLock {
    private class Node {
        private Thread thread;

        public Node() {
            thread = Thread.currentThread();
        }

        private volatile boolean isLocked = true;

        @Override
        public String toString() {
            return thread.getName() +
                    ", isLocked=" + isLocked;
        }
    }

    private volatile AtomicReference<Node> tail = new AtomicReference<>();
    private volatile ThreadLocal<Node> local = new ThreadLocal<>();

    private void lock() {
        Node current = new Node();
        local.set(current);
        Node prev = tail.getAndSet(current);
        if (prev != null) {
            while (prev.isLocked) {

            }
        }
    }

    private void unLock() {
        Node current = local.get();
        if (!tail.compareAndSet(current, null)) {
            current.isLocked = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyCLHLock lock = new MyCLHLock();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unLock();
            }));
        }

        for (Thread thread : threads) {
            thread.start();
            Thread.sleep(10);
        }
    }
}

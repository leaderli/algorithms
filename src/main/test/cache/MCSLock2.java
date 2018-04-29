package cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MCSLock2 {
    class Node {
        private final Thread thread;

        public Node() {
            this.thread = Thread.currentThread();
        }

        private AtomicReference<Node> next = new AtomicReference<>();

        private volatile boolean block = true;

        @Override
        public String toString() {
            String str = thread.getName() + "{";
            if (next != null) {
                str += "next=" + next + " ";
            }
            str += " " + block + "}";
            return str;
        }
    }

    private AtomicReference<Node> tail = new AtomicReference<>();

    public void lock() {
        Node node = new Node();
        if (!tail.compareAndSet(null, node)) {
            Node next = tail.get();
            while (!next.next.compareAndSet(null, node)) {
                next = next.next.get();
            }
            while (node.block) {

            }
        }
    }

    public void unlock() {
        Node node = tail.get();
        if (node.next.get() != null) {
            node.next.get().block = false;
            tail.set(node.next.get());
        } else {
            tail.set(null);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MCSLock2 lock = new MCSLock2();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

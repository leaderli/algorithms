package cache.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MyMCSLock {
    class MCSNode {
        private Thread thread;

        MCSNode() {
            this.thread = Thread.currentThread();
        }

        MCSNode next;
        volatile boolean isBlocked = true;

        @Override
        public String toString() {
            return thread.getName() + "{" +
                    "next=" + next +
                    ", isBlocked=" + isBlocked +
                    '}';
        }
    }

    private volatile AtomicReference<MCSNode> tail = new AtomicReference<>();
    private volatile ThreadLocal<MCSNode> local = new ThreadLocal<>();

    public void lock() {
        MCSNode current = new MCSNode();
        local.set(current);
        tail.get();
        if (tail.compareAndSet(null, current)) {
            return;
        }
        MCSNode head;
        while ((head = tail.get()) == null) {

        }
        while (head.next != null) {
            head = head.next;
        }
        System.out.println("lock " + head);
        head.next = current;
        System.out.println("next " + head);
        while (current.isBlocked) {

        }
    }

    private boolean unLock() {
        MCSNode current = local.get();
        MCSNode head = tail.get();
        System.out.println("unlock "+current);
        if (current == head) {
            while (current != null && current.next != null) {

                if (tail.compareAndSet(current, current.next)) {
                    current.next.isBlocked = false;
                    current = null;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyMCSLock lock = new MyMCSLock();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!lock.unLock()){
                    lock.unLock();
                }

            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

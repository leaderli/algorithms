package cache;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class TiketLock {
    private AtomicInteger ticket = new AtomicInteger();
    private AtomicInteger service = new AtomicInteger();
    private int status;

    public void lock() {
        int now = ticket.getAndIncrement();
        while (service.get() != now) {
        }
        status = now;
    }

    public void unLock() {
        int next = status +1;
        service.compareAndSet(status, next);
        System.out.println(Thread.currentThread().getName() + " unLock");
    }

    public static void main(String[] args) throws InterruptedException {
        TiketLock lock = new TiketLock();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unLock();
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Test
    public void test1() {
        class Dog {
            public volatile String name = "dog";
        }
        Dog dog = new Dog();
        AtomicReferenceFieldUpdater<Dog, String> updater = AtomicReferenceFieldUpdater.<Dog,String>newUpdater(Dog.class, String.class, "name");
        System.out.println(dog.name);
        updater.compareAndSet(dog, "d", "test");
        System.out.println(dog.name);
        updater.compareAndSet(dog, "dog", "test");
        System.out.println(dog.name);
        System.out.println(updater.getAndSet(dog, "fuck"));
        System.out.println(dog.name);
    }
}

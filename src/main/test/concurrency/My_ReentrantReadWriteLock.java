package concurrency;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class My_ReentrantReadWriteLock {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000, "6222612345678");
        for (int i = 0; i < 10; i++) {
            account.getCash();
            account.getCash();
            account.getCash();
            account.setCash(500);
        }
    }
}

class Account {
    private volatile int cash;
    private String name;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Account(int cash, String name) {
        this.cash = cash;
        this.name = name;
    }

    public int getCash() {
        new Thread(() -> {
            lock.readLock().lock();
            try {
                park();
                System.out.println(Thread.currentThread().getName() + "|" + name + " count ---> get" + cash);
            } finally {
                lock.readLock().unlock();
            }
        }).start();
        return cash;
    }

    public void setCash(int cash) {
        new Thread(() -> {
            lock.writeLock().lock();
            try {
                park();
                park();
                this.cash += cash;
                System.out.println(Thread.currentThread().getName() + "|" + name + " count--->" + this.cash);
            } finally {
                lock.writeLock().unlock();
            }
        }).start();
    }

    Random random = new Random();
    private void park(){
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

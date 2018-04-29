package concurrency.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Depot {
    private int size;
    private Lock lock;
    private Condition full;
    private Condition empty;
    private int capacity = 90;

    private Depot(int size, Lock lock) {
        this.size = size;
        this.lock = lock;
        this.full = lock.newCondition();
        this.empty = lock.newCondition();
    }

    public void produce(int i) {
        lock.lock();
        try {
            int left = i;
            while (left > 0) {
                while (size >= capacity) {
                    full.await();
                }

                int inc = (size + left) > capacity ? (capacity - size) : left;
                size += inc;
                left -= inc;
                System.out.println(Thread.currentThread().getName() + " "+i +" "+inc+" "+left+ " "+size +" "+capacity);
                empty.signalAll();
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void consume(int i) {
        lock.lock();
        try {
            int left = i;
            while (left > 0) {
                while (size <= 0) {
                    empty.await();
                }
                int inc = left > size ? size : left;
                size -= inc;
                left -= inc;
                System.out.println(Thread.currentThread().getName() + "<---consume " + i + " now=" + this.size);
                full.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        Depot depot = new Depot(0, new ReentrantLock());
        Producer producer = new Producer(depot);
        Customer customer = new Customer(depot);
        producer.produce(60);
        producer.produce(120);
        customer.consume(90);
        customer.consume(150);
        producer.produce(110);

    }
}

class Producer {
    private Depot depot;

    Producer(Depot depot) {
        this.depot = depot;
    }

    public void produce(int size) {
        new Thread(() -> depot.produce(size)).start();
    }
}

class Customer {
    private Depot depot;

    Customer(Depot depot) {
        this.depot = depot;
    }

    public void consume(int size) {
        new Thread(() -> depot.consume(size)).start();
    }
}

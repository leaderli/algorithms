package cache.p1;

import java.text.ParseException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class SynchronizedNode {
    public synchronized void s1(){
        this.i = 100;
    }
    public void s2(){
        synchronized (this){

        this.i = 100;
        }
    }
    private int i = 100;

    public static void main(String[] args) throws ParseException {
        new SynchronizedNode().spinLock();
    }

    private void spin(){
        boolean flag = true;
        while (flag){

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("hell");
    }
    private void park(){
        boolean flag = true;
        while (flag){
            LockSupport.park();
        }
        System.out.println("hell");
    }
    private void yeild(){
        boolean flag = true;
        while (flag){
            Thread.yield();
        }
        System.out.println("hell");
    }

    private void spinLock(){
        AtomicReference<Thread> owner = new AtomicReference<Thread>();//持有自旋锁的线程对象
        Thread thread = Thread.currentThread();
        owner.compareAndSet(null, thread);
        int i = 0;
        while (!owner.compareAndSet(null, thread)){
            LockSupport.parkUntil(1000_000_000_000_0L);
            System.out.println(i++);
        }
        System.out.println("hell");

    }
}

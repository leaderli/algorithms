package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MyCLHLock1 {
    class CLHNode {
        public volatile boolean isBlocked = true;

        @Override
        public String toString() {
            return Thread.currentThread().getName()+"{" +
                    "isBlocked=" + isBlocked +
                    '}';
        }
    }

    private AtomicReference<CLHNode> tail = new AtomicReference<>();
    private ThreadLocal<CLHNode> local = new ThreadLocal<>();
    public void lock(){
        CLHNode node = new CLHNode();
        CLHNode prev = tail.getAndSet(node);
        if(prev!=null){
            System.out.println(node +" is Block");
            while (prev.isBlocked){

            }
        }
        local.set(node);
        System.out.println(node +" lock");
    }
    public void unLock(){
       CLHNode node = local.get();
        System.out.println(node +" unlock");
       node.isBlocked = false;
    }
    public static void main(String[] args) {
        MyCLHLock1 lock = new MyCLHLock1();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
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

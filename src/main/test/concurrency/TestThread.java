package concurrency;

import junit.framework.TestCase;
import org.omg.PortableServer.THREAD_POLICY_ID;

public class TestThread extends TestCase {
    private static class Tickets extends Thread {
        private int count = 10;

        @Override
        public void run() {
            while (count > -1) {
                System.out.println(Thread.currentThread().getName() + " 买票  " + count--);
            }
        }
    }

    private class RunnableThread implements Runnable {

        private int count = 10;

        @Override
        public void run() {

            while (count > -1) {
                System.out.println(Thread.currentThread().getName() + " 买票  " + count--);
            }
        }
    }

    public void test1() {
        Thread t1 = new Tickets();
        Thread t2 = new Tickets();
        Thread t3 = new Tickets();
        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");
        t1.start();
        t2.start();
        t3.start();
    }

    public void test2() {
        Runnable runnable = new RunnableThread();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");
        t1.start();
        t2.start();
        t3.start();
    }

    public void test3() {
        Runnable r1 = () -> {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        };

        Thread t1 = new Thread(r1, "r1");
        Thread t2 = new Thread(r1, "r2");
        t1.start();
        t2.start();
    }

    private class syncThread extends Thread {
        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {

                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void test4() {

        Thread t1 = new syncThread();
        Thread t2 = new syncThread();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

    static class Count {
        synchronized void syncMethod() {
            System.out.println(this);
            printThreadName();
        }

        void noSyncMethod() {
            System.out.println(this);
            printThreadName();
        }

        private void printThreadName() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName()+" run");
                    while (true){

                    }
                }
            }
        };


        Thread thread = new Thread(r1, "r1");
        synchronized (r1) {
            System.out.println(Thread.currentThread().getName() + " start");
            thread.start();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " waite");
            r1.wait(3000);
            System.out.println(Thread.currentThread().getName() + " continue");
        }
    }

}

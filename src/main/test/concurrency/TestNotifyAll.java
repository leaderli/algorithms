package concurrency;

public class TestNotifyAll {
    private static Object obj = new Object();

    private static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " " + "run");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + "wait");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " " + "interrupt");
                }
                System.out.println(Thread.currentThread().getName() + " " + "continue");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadA("t1");
        Thread t2 = new ThreadA("t2");
        Thread t3 = new ThreadA("t3");
        t1.start();
        t2.start();
        t3.start();
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+" sleep 6000");
        Thread.sleep(6000);
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }
}

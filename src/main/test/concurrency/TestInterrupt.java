package concurrency;

public class TestInterrupt {
    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                int i = 0;

                    while (!isInterrupted()) {
                        try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        } catch (InterruptedException e) {
                            System.out.println("end");
                        }
                    }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("t1");
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
    }
}

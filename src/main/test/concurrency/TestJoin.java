package concurrency;

public class TestJoin{
    private static Object obj = new Object();

    private static class ThreadA extends Thread {
        private Thread t2;
        public ThreadA(String name) {
            super(name);
        }

        public void setT2(Thread t2) {
            this.t2 = t2;
        }

        @Override
        public void run() {
            synchronized (obj) {
                if(t2!=null){
                    t2.start();
                    try {
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.setT2(t2);
        t1.start();
        t1.join();
        System.out.println("111");
    }
}

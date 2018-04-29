package concurrency;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class TestThreadState extends TestCase {
    private static int STATE = 0;

    public void test1() throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (this) {

                    Random random = new Random();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = random.nextInt(100) / 4;
                }
            }
        };
        Monitor monitor = new Monitor(thread);
        System.out.println("current " + thread.getState());
        monitor.start();
        Thread.sleep(200);
        synchronized (thread) {
            thread.start();
            thread.join();
        }

        for (Thread.State state : monitor.getStates()) {
            System.out.println("monitor=" + state);
        }
    }


    private class Monitor extends Thread {
        private Thread thread;
        private Set<State> states = new HashSet<>();

        public Monitor(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println("monitor");
            while (true) {
                synchronized (thread) {

                    State tmp = thread.getState();
                    states.add(tmp);
                }
            }
        }

        public Set<State> getStates() {
            return states;
        }
    }
}

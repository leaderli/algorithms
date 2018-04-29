package concurrency;

import sun.tools.jstat.ExpressionExecuter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadGroup {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadGroup group = new ThreadGroup("g1");
        Thread thread = new MyThread(group,null);
        thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("caught");
            System.out.println(t.getName());
            System.out.println(t.getState());
            System.out.println(e.getMessage());
        });
        try {
            thread.start();
            group.list();
        }catch (Exception e){
            System.out.println("catch");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<?> future= executorService.submit(() -> {
            System.out.println("submit");
        });
        boolean done = future.isDone();
        System.out.println(done);

    }
    private static class MyThread extends Thread{
        public MyThread(ThreadGroup group, Runnable target) {
            super(group, target);
        }

        @Override
        public void run() {
            int i = 0;
            i = 10/i;
        }
    }
}

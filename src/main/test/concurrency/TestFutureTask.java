package concurrency;

import junit.framework.TestCase;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask extends TestCase {

    public void test1() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int result = 1;
            for (int i = 0; i < 100; i++) {
                result += i;
            }
            Thread.sleep(3000);
            return result;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        Thread.sleep(1000);
        System.out.println("Thread " + Thread.currentThread().getName() + " is Running");
        if (!futureTask.isDone()) {
            System.out.println("Task is not done");
            Thread.sleep(2000);
        }
        int result;
        result = futureTask.get();
        System.out.println(result);
    }
    private static Unsafe unsafe;

    static {
        try {
            //通过反射获取rt.jar下的Unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error"+ e);
        }
    }
    public void test2() throws NoSuchFieldException {
    }
}

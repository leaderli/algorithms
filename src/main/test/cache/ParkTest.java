package cache;


import java.util.concurrent.locks.LockSupport;

public class ParkTest {
    private static Thread mainThread;
    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
               setName("temp");
                System.out.println(Thread.currentThread().getName()+"-unPark");
                LockSupport.unpark(mainThread);
            }
        };

        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"-start");
        t.start();
        System.out.println(Thread.currentThread().getName()+"-park");
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName()+"-continue");

    }
}

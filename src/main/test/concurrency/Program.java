package concurrency;

import java.io.IOException;

/**
 * Created by li on 11/30/17.
 */
public class Program {

    public static void main(String[] args) throws IOException {
       class TestThread extends Thread{
           @Override
           public void run() {
               System.out.println("test");
           }
       }
       Thread t = new TestThread();
       t.setDaemon(true);
       t.start();
       System.in.read();

    }
}

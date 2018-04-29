package concurrency;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by li on 12/7/17.
 */
public class TestRegex {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("123.0");
        System.out.println(bigDecimal.toPlainString());
    }
}

class Lock{
    private Stack<Thread> stack = new Stack<>();
    private volatile boolean flag = true;
    public void await(){
        while (flag){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void aNotify(){

    }

}
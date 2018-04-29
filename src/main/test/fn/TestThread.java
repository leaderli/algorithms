package fn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by li on 11/16/17.
 */
public class TestThread {
    private static final ThreadLocal<DateFormat> DF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static void main(String[] args) {
        System.out.println(DF.get().format(new Date()));
    }
}


class MyThread extends Thread{


}

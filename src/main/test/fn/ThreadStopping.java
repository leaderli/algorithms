package fn;

/**
 * Created by li on 11/29/17.
 */
public class ThreadStopping {
    private void test() {
        this.<String>set("1");
    }

    private <T> void set(T t) {
        System.out.println(t);
    }
}

package fn;

/**
 * Created by li on 11/30/17.
 */
public class TestThreadLocal {
    private static volatile ThreadLocal<String> uuid = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                if("a".equals(name))
                    uuid.set("foxtrot");
                else
                    uuid.set("charlie");
                System.out.println(uuid.get());
            }
        };

        Thread a = new Thread(r);
        a.setName("a");
        Thread b = new Thread(r);
        b.setName("b");
        a.start();
        b.start();

    }
}

package concurrency;

public class PossibleReordering {
    static int count = 0;
    int x = 0, y = 0;
    int a = 0, b = 0;

    public void test() throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("第" + count + "次 (" + x + "," + y + ")");
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {

            PossibleReordering possibleReordering = new PossibleReordering();
            possibleReordering.test();
            if (possibleReordering.x == possibleReordering.y && possibleReordering.x == 0) {
                break;
            }
            PossibleReordering.count++;
        }
    }
}

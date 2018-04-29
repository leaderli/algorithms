package concurrency;

public class Reordering {
    public static void main(String[] args) {
        while (true) {
            try {


                int x, y;
                x = 1;
                try {
                    x = 2;
                    y = 0 / 0;
                } finally {
                    System.out.println(x);
                    if (x != 2) {

                        return;
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
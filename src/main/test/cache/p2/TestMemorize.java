package cache.p2;

import org.junit.Test;

public class TestMemorize {
    public static void main(String[] args) {
        Computable<String,String> computable = new Memorize(new UseCostStaComputer());
        System.out.println("fuck".hashCode());
        System.out.println(computable.compute("fuck").hashCode());
        System.out.println(computable.compute("fuck").hashCode());
        System.out.println(computable.compute("1000002").hashCode());

    }

    @Test
    public void test1(){
        int a = 1;
        for (int i = 1; i <17 ; i++) {
            System.out.println(a <<i);
        }
    }
}

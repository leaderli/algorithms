package concurrency;

import junit.framework.TestCase;

/**
 * Created by li on 12/9/17.
 */
public class StandardTest extends TestCase{

    /**
     * a >10
     * b ==null
     * c eq 1 ,3
     * d noteq 2
     * e eq '' null
     * f noteq null ''
     */
    public void test1(){
       int a =1;
       String b = "c";
       String c = "2";
       String d= null;
       String f = null;
       boolean exist = a>10;
       exist = exist&&b==null;
       exist = exist&& ("1".equals(c)||"3".equals(c));
       exist = exist&&!("2".equals(d));
    }
}

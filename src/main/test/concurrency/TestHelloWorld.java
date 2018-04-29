package concurrency;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by li on 9/23/17.
 */
public class TestHelloWorld extends TestCase{
   public void testMain(){
      Set<String> set = new HashSet<String>();
      set.add("");
      set.add("1");
      System.out.println(set.contains(""));
      System.out.println(set.contains("1"));
   }

}

package algorithms;

import li.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class factorial {
	long nano;
	public int exe(int n, int count){
		if(n == 1){
			return count;
		}
		if(count<1){
			count=1;
		}
		return exe(n-1, count*n);
	}

	public int exe2(int n){
		if(n == 1){
			return 1;
		}
		int count = n*exe2(n-1);
		if(count<1)
			return 1;
		return count;
	}
	@Before
	public void before(){
		nano = System.nanoTime();
	}
	@After
	public void after(){
		Log.print(System.nanoTime()- nano);
		Log.printLine();
	}
	@Test
	public void test(){

	}
	@Test
	public void test1(){
		Log.println("exe2");
		Log.println(exe2(1000));
	}
	@Test
	public void test2(){
		Log.println("exe");
		Log.println(exe(1000, 1));
	}
}

package algorithms;

import li.Log;
import org.junit.Test;

import java.util.Stack;

public class Hanoi {

	@Test
	public void test1(){
			move(1, "A", "C", "B");
		Log.printLine();
		move(2, "A", "C", "B");
		Log.printLine();
		move(3, "A", "C", "B");
		Log.printLine();
		move(4, "A", "C", "B");
	}


	public static void move(int dish,String from,String temp,String to){
		if(dish == 1){
			System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
		}else{
			move(dish-1,from,to,temp);//A为初始塔座，B为目标塔座，C为中介塔座
			System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
			move(dish-1,temp,from,to);//B为初始塔座，C为目标塔座，A为中介塔座
		}
	}
}

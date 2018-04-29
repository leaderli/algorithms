package fn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestFn {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.forEach(System.out::println);
		Predicate<Integer> p1 = (i)->i>2;
		Predicate<Integer> p2 = (i)->i<4;
		list.stream().filter(p1.and(p2)).forEach(System.out::println);
	}
}

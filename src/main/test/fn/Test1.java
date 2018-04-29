package fn;

public class Test1 {
	public static void main(String[] args) {
		new Test1().t1("1","2");
	}

	private void t1(String ... aaa){
		for (String s:aaa){
			System.out.println("fuck");
		}
	}
}

package algorithms;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
	private Object[] obj;
	private int top = -1;
	private int capacity;

	public MyStack(int capacity) {
		this.capacity = capacity;
		obj = new Object[capacity];
	}

	public void push(T t) {

		if (top == capacity - 1) {
			capacity = capacity * 2;
			Object[] newObj = new Object[capacity];
			for (int i = 0; i <= top; i++) {
				newObj[i] = obj[i];
			}
			obj = newObj;
		}
		obj[++top] = t;
	}

	public T pop() {
		return (T) obj[top--];
	}

	public T peek() {
		return (T) obj[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == capacity - 1;
	}

	public static void main(String[] args) {
		test("12345");
		test("{12345}");
		test("{12[3]45}");
		test("{12[3>]45}");
		test("{12[3<]45}");
		test("{12[3]45}<");
	}

	public static void test(String input) {
		int length = input.length();
		if (length < 1) {
			System.out.println("not");
			return;
		}
		List<Character> left = new ArrayList<>();
		left.add('{');
		left.add('[');
		left.add('<');
		List<Character> right = new ArrayList<>();
		right.add('}');
		right.add(']');
		right.add('>');
		MyStack<Character> myStack = new MyStack(length);
		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);
			if (left.contains(c)) {
				myStack.push(c);
			} else if (right.contains(c)) {
				char temp = myStack.pop();
				if (left.indexOf(temp) != right.indexOf(c)) {
					System.out.println("not");
					return;
				}
			}
		}
		if (myStack.isEmpty()) {

			System.out.println("ok");
		} else
			System.out.println("not");
	}
}

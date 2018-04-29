package algorithms;

import java.util.Arrays;

public class Deque<T> {
	private int capacity;
	private Object arr[];
	private int left;
	private int right;
	private int length;

	public Deque(int capacity) {
		arr = new Object[capacity];
		this.capacity = capacity;
		left = -1;
		right = 1;
		length = 0;
	}

	public int getLength() {
		return length;
	}

	public boolean isFull() {
		return length == capacity;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public T peekLeft() {
		return (T) arr[left];
	}

	public T peekRight() {
		return (T) arr[right];
	}

	public void pushLeft(T t) {
		if (isFull()) {
			System.out.println("-----full------  " + this);
			return;
		}
		arr[++left] = t;
		if (++length == 1) {
			right = left;
		}

		if(left>capacity){
			left= left-capacity;
		}
		System.out.println(this);

	}

	public void pushRight(T t) {
		if (isFull()) {
			System.out.println("-----full------  " + this);
			return;
		}
		--right;
		if (right < 0) {
			right = capacity + right;
		}
		arr[right] = t;
		if (++length == 1) {
			left = right;
		}
		if(right>capacity){
			right= right-capacity;
		}
		System.out.println(this);
	}

	public T popLeft() {
		if (isEmpty()) {
			System.out.println("-----empty------" + this);
			return null;
		}
		T t = (T) arr[left];
		arr[left] = null;
		left--;
		length--;
		if(left <0){
			left = capacity+left;
		}
		System.out.println(this);
		return t;
	}

	public T popRight() {
		if (isEmpty()) {
			System.out.println("-----empty------" + this);
			return null;
		}
		T t = (T) arr[right];
		arr[right] = null;
		right--;
		length--;
		if(right<0){
			right= capacity+right;
		}
		System.out.println(this);
		return t;
	}
	@Override
	public String toString() {
		return "Deque{" +
				"capacity=" + capacity +
				", arr=" + Arrays.toString(arr) +
				", left=" + left +
				", right=" + right +
				", length=" + length +
				'}';
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>(4);
		deque.pushLeft(1);
		deque.pushLeft(2);
		deque.pushRight(3);
		deque.pushRight(4);
		deque.popLeft();
		deque.popRight();
		for (int i = 0; i <10 ; i++) {

		int h  = i;
		int length = 4;
		h = (h+1)&(length-1);
		System.out.println(h);
		}
	}

	private static void ttt(String... str) {
		for (String s : str) {
			System.out.println(s);
		}
	}
}

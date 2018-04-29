package algorithms;

import org.junit.Test;

import java.util.Arrays;

public class AndQueue<T> {
	private Object elements[];
	private int head;
	private int tail;
	private int capacity = 1 << 1;
	private int length;

	public AndQueue(int size) {
		if (size > capacity) {
			capacity = size;
			capacity |= capacity >>> 1;
			capacity |= capacity >>> 2;
			capacity |= capacity >>> 4;
			capacity |= capacity >>> 8;
			capacity |= capacity >>> 16;
			capacity++;
			if (capacity < 0) {
				capacity >>>= 1;
			}
		}
		elements = new Object[capacity];
	}

	public void push(T t) {
		if (capacity == length) {
			System.out.println(this + " full");
			return;
		}
		elements[head] = t;
		length++;
		head = (head + 1) & (capacity - 1);
		System.out.println(this);
	}

	public T pop() {
		T t = (T) elements[tail];
		if (t == null) {
			return null;
		}
		elements[tail] =null;
		tail = (tail + 1) & (capacity - 1);
		length--;
		System.out.println(this);
		return t;
	}

	@Override
	public String toString() {
		return "AndQueue{" +
				"elements=" + Arrays.toString(elements) +
				", head=" + head +
				", tail=" + tail +
				", capacity=" + capacity +
				'}';
	}

	@Test
	public void test1() {
		System.out.println(1 << 1);
	}

	public static void main(String[] args) {
		AndQueue<Integer> queue = new AndQueue<>(3);
		queue.push(1);
		queue.push(2);
		queue.push(3);//queArray数组数据为[1,2,3]
		queue.push(3);//queArray数组数据为[1,2,3]

		queue.pop();//queArray数组数据为[null,2,3]
		queue.pop();//queArray数组数据为[null,2,3]
		queue.push(4);//queArray数组数据为[4,2,3]
		queue.pop();//queArray数组数据为[null,2,3]
		queue.pop();//queArray数组数据为[null,2,3]

		queue.push(4);//queArray数组数据为[4,2,3]
		queue.push(5);//队列已满,queArray数组数据为[4,2,3]
	}
}

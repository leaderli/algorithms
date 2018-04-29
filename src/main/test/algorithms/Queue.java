package algorithms;

import java.util.Arrays;

public class Queue<T> {
	private Object[] arr;
	private int capacity;
	private int head;
	private int tail;
	private int length;

	public Queue(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
		head = 0;
		tail = -1;
		length = 0;
	}

	public void push(T t) {
		if (isFull()) {
			System.out.println("queue is full");
			return;
		}
		if (tail == capacity - 1) {
			tail = -1;
		}
		arr[++tail] = t;
		length++;
		System.out.println(Arrays.toString(arr));
	}

	public T pop() {
		if (isEmpty()) {
			return null;
		}
		T t = (T) arr[head];
		arr[head] = null;
		if (++head == capacity) {
			head = 0;
		}
		length--;
		System.out.println(Arrays.toString(arr));
		return t;
	}

	public T peep() {
		if (isEmpty()) {
			return null;
		}
		return (T) arr[head];
	}

	public int getLength() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public boolean isFull() {
		return length == capacity;
	}


	public static void main(String[] args) {
		Queue queue = new Queue(3);
		queue.push(1);
		queue.push(2);
		queue.push(3);//queArray数组数据为[1,2,3]
		queue.push(3);//queArray数组数据为[1,2,3]

		System.out.println(queue.peep()); //1
		queue.pop();//queArray数组数据为[null,2,3]
		queue.pop();//queArray数组数据为[null,2,3]
		queue.push(4);//queArray数组数据为[4,2,3]
		queue.pop();//queArray数组数据为[null,2,3]
		queue.pop();//queArray数组数据为[null,2,3]
		System.out.println(queue.peep()); //2

		queue.push(4);//queArray数组数据为[4,2,3]
		queue.push(5);//队列已满,queArray数组数据为[4,2,3]
	}
}


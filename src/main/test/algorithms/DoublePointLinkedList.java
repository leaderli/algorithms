package algorithms;

import li.Log;
import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;

public class DoublePointLinkedList {
	public Node head;
	public Node tail;
	public int size;

	private class Node {
		private Node prev;
		private Node next;
		private Object data;

		public Node(Object data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}

	public void addHead(Object data) {
		Node current = new Node(data);
		if (head == null) {
			head = tail = current;
		} else {
			head.prev = current;
			current.next = head;
			head = current;
		}
		size++;
		display();
	}

	public void addTail(Object data) {
		Node current = new Node(data);
		if (tail == null) {
			head = tail = current;
		} else {
			tail.next = current;
			current.prev = tail;
			tail = current;
		}
		size++;
		display();
	}

	public boolean removeHead() {
		if (head != null) {
			Node node = head.next;
			if (node == null) {
				head = tail = null;
			} else {
				node.prev = null;
				head.next = null;
				head = node;
			}
			size--;
			display();
			return true;
		}
		return false;
	}

	public boolean removeTail() {
		if (tail != null) {
			Node node = tail.prev;
			if (node == null) {
				head = tail = null;
			} else {
				node.next = null;
				tail.prev = null;
				tail = node;
			}
			size--;
			display();
			return true;
		}
		return false;
	}

	public boolean remove(Object data) {
		Node current = head;
		while (current != null) {
			if (ObjectUtils.equals(current.data, data)) {
				if (current.prev == null) {
					return removeHead();
				} else if (current.next == null) {
					return removeTail();
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					current.prev = null;
					current.next = null;
					size--;
					display();
					return true;
				}

			}
			current = current.next;
		}
		return false;
	}

	public void display() {
		Node display = head;
		while (display != null) {
			Log.print(display + "->");
			display = display.next;
		}
		Log.println();
		Log.printLine();

	}

	@Test
	public void test1() {
		DoublePointLinkedList linkedList = new DoublePointLinkedList();
		linkedList.addHead("A");
		linkedList.addHead("B");
		linkedList.addHead("C");
		linkedList.addTail("D");
		linkedList.addTail("E");
		linkedList.remove("C");
		linkedList.remove("E");
		linkedList.remove("A");
	}
}

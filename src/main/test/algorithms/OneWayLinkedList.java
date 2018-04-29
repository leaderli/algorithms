package algorithms;

import li.Log;
import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;

public class OneWayLinkedList {

	private int size;

	private Node head = null;

	private class Node {
		private Object data;

		private Node next;

		private Node(Object data) {
			this.data = data;
		}
	}

	public void add(Object value) {
		if (head == null) {
			head = new Node(value);
		} else {
			Node node = head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new Node(value);
		}
		size++;
	}

	public boolean remove(Object value) {
		if (size == 0) {
			return false;
		}
		Node current = head;
		Node prev = head;

		while (true) {
			if (ObjectUtils.equals(value, current.data)) {
				if (prev == current) {
					current = null;
					head = null;
				} else {
					prev.next = current.next;
					current = null;
				}
				size--;
				return true;
			} else {
				if (current.next == null) {
					return false;
				}
				prev = current;
				current = current.next;
			}
		}
	}

	public int size() {
		return size;
	}

	public void display() {
		if (size > 0) {
			Node node = head;
			StringBuffer sb = new StringBuffer("[");
			while (true) {
				sb.append(node.data);
				node = node.next;
				if (node != null) {
					sb.append("->");
					continue;
				}
				break;
			}
			sb.append("]");
			Log.println(sb);
		} else {
			Log.println("[]");
		}
	}

	@Test
	public void test1() {
		OneWayLinkedList list = new OneWayLinkedList();
		list.display();
		list.add("A");
		list.display();
		list.add("B");
		list.display();
		list.add("C");
		list.display();
		list.add("D");
		list.display();
		list.remove("D");
		list.display();
		list.remove("B");
		list.display();
		list.remove("C");
		list.display();
		list.remove("C");
		list.display();
		list.remove("A");
		list.display();
	}
}

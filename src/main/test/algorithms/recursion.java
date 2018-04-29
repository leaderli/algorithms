package algorithms;

import org.junit.Test;

public class recursion {
	int[] arr;

	public int find(int begin, int end, int value) {
		if (begin >= end) {
			return -1;
		}
		int cursor = begin;
		if (arr[cursor] < value) {
			cursor = end;
			if (value < arr[cursor]) {
				cursor = (end - begin) / 2 + begin;
				if (arr[cursor] == value) {
					return cursor;
				}
				if (value < arr[cursor]) {
					return find(begin + 1, cursor, value);
				}

				return find(cursor, end - 1, value);
			}
		}
		if (arr[cursor] == value){
			return cursor;
		}
		return -1;
	}

	public int find(int value) {
		return find(0, arr.length - 1, value);
	}

	@Test
	public void test1() {
		arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " == " + find(i));
		}
	}
}

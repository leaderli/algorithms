package algorithms;

import li.Log;
import org.junit.Test;

import java.util.Arrays;

public class MergeSort {

	public int[] sort(int[] c, int start, int last, int mid) {
		if (start < last) {
			int length = c.length;
			if (length > 2) {
			}
			int[] temp = new int[length];

			for (int i = 0; i < length; i++) {
				if (start == mid) {
					temp[i] = c[last++];
				} else if (last == length) {
					temp[i] = c[start++];
				} else if (c[start] > c[last]) {
					temp[i] = c[last++];
				} else {
					temp[i] = c[start++];
				}
			}
			return temp;
		}
		return c;
	}

	@Test
	public void test1() {
		int[] c = {1, 2, 4, 7, 4, 3, 6, 9,1};
		merge(c);
		Log.println(Arrays.toString(c));
	}

	public void merge(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	private void mergeSort(int[] arr, int from, int to) {
		if (to <= from) {
			return;
		}
		Log.printLine();
		Log.println("form=" + from + " to=" + to);
		int mid = (to - from) / 2 + from;
		mergeSort(arr, from, mid);
		mergeSort(arr, mid + 1, to);
		merge(arr, from, to);
	}

	public void merge(int[] arr, int from, int to) {
		if (to < from) {
			return;
		}
		int[] temp = new int[to - from + 1];
		int i = 0;
		int first = from;
		int mid = (to - from) / 2 + from;
		int second = mid + 1;
		while (first <= mid && second <= to) {

			if (arr[first] < arr[second]) {
				temp[i++] = arr[first++];
			} else {
				temp[i++] = arr[second++];
			}
		}

		while (first <= mid) {

			temp[i++] = arr[first++];
		}
		while (second <= to) {
			temp[i++] = arr[second++];
		}
		Log.println(Arrays.toString(temp));
		for (int j = 0; j < temp.length; j++) {
			arr[from++] = temp[j];
		}

	}
}

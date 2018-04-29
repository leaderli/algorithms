package algorithms;

import junit.framework.TestCase;

import java.util.Arrays;

public class Sort extends TestCase {
	int[] arr = {4,2,8,9,5,7,6,1,3};
	public void testBubble() {
		System.out.println("冒泡排序");
		for (int i = 0; i < arr.length; i++) {
			System.out.print("这是第"+(i)+"次排序: ");
			System.out.println(Arrays.toString(arr));
			boolean flag = true;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[i]> arr[j]){
					int t = arr[i];
					arr[i] = arr[j];
					arr[j] = t;
					flag = false;
				}
			}
			if(flag)
				break;
		}
	}
	public void testSelect() {
		System.out.println("选择排序");
		for (int i = 0; i < arr.length; i++) {
			System.out.print("这是第"+(i)+"次排序: ");
			System.out.println(Arrays.toString(arr));
			int temp = i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[j]<arr[temp]){
					temp = j;
				}
			}
			if(temp!=i){
				int t = arr[i];
				arr[i] = arr[temp];
				arr[temp] = t;
			}
		}
	}
	public void testInsert() {
		System.out.println("插入排序");
		int j;
		for (int i = 1; i < arr.length; i++) {
			System.out.print("这是第"+(i)+"次排序: ");
			System.out.println(Arrays.toString(arr));
			j = i;
			int temp = arr[i];
			while (j>0&&temp<arr[j-1]){
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = temp;
			System.out.println(i-j);
		}
		System.out.println(Arrays.toString(arr));
	}
}


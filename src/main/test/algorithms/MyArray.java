package algorithms;

public class MyArray implements Array<Integer> {
	private int[] arr;
	private int capacity;
	private int size;

	public MyArray() {
		this(16);
	}

	public MyArray(int capacity) {
		this.capacity = capacity;
		arr = new int[capacity];
		size = 0;

	}


	@Override
	public void add(Integer integer) {
		if (size == capacity) {
			capacity = capacity * 2;
			System.out.println(capacity);
			int[] expand = new int[capacity];
			for (int i = 0; i < size; i++) {
				expand[i] = arr[i];
			}
			arr = expand;
		}
		arr[size] = integer;
		size++;
	}

	@Override
	public Integer remove(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int result = get(index);
		size--;
		for (int i = index; i < size; i++) {
			arr[index] = arr[index + 1];
		}
		return result;
	}

	@Override
	public Integer delete(Integer integer) {
		int index = find(integer);
		if (index == -1){
			return null;
		}
		return remove(index);
	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public Integer get(int index) {
		if (index > -1 && index < size) {
			return arr[index];
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	public int find(Integer integer) {
		for (int i = 0; i < size; i++) {
			if (arr[i] == integer)
				return i;
		}
		return -1;
	}

	@Override
	public void show() {
		String str="";
		for (int i = 0; i <size ; i++) {
			str+=","+arr[i];
		}
		if(str.length()>1){
			System.out.println(str.substring(1));
		}
	}

	public static void main(String[] args) {
		MyArray myArray = new MyArray(4);
		myArray.add(1);
		myArray.show();
		myArray.add(2);
		myArray.show();
		myArray.add(3);
		myArray.show();
		myArray.add(4);
		myArray.show();
		myArray.add(5);
		myArray.show();
		myArray.remove(1);
		myArray.show();
		myArray.delete(4);
		myArray.show();
		System.out.println(myArray.size);
	}
}

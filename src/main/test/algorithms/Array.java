package algorithms;

public interface Array<T>{
	void add(T t);
	T remove(int index);
	T delete(T t);
	int length();
	T get(int index);
	int find(T t);
	void show();
}

package cache.p1;

public class Node {
    Thread thread;
    Node next;
    Node(Thread thread){
        this.thread = thread;
    }
}

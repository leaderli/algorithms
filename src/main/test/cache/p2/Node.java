package cache.p2;

public class Node {
    private int id;

    public Node prev;
    public Node next;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node" +
                "" + id +
                ", prev=" + (prev == null ? "null" : prev.id) +
                ", next=" + (next == null ? "null" : next.id)+"" ;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        System.out.println(node1);
        System.out.println(node2);
        System.out.println(node3);

        Node prev = node2;
        node3.prev = prev = prev.prev;

        System.out.println(node1);
        System.out.println(node2);
        System.out.println(node3);
        System.out.println("prev"+prev);
    }
}

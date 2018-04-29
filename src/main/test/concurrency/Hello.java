package concurrency;

public class Hello {
    public static void main(String[] args) {
        Node node = new Node();
        Node node1;
        do{
            node1 = node = node.prev;
        }while (node.status>0);
        System.out.println(node1);
    }
}
class Node {
    int status;
    Node prev;
}

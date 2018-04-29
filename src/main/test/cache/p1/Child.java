package cache.p1;

public class Child extends Father{
    @Override
    public void init(int i) {
        a = i+100;
        ttt();
    }

    public static void main(String[] args) {
        Father father = new Child();
        father.init(40);
        System.out.println(father.a);
    }
}

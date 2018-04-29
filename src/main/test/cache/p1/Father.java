package cache.p1;

public class Father {
    int a;
    public void init(int i){
        a = i;
    }

    public void ttt(){
        a = 11;
        aaaa();
    }

    public void aaaa(){
        a = 22;
        System.out.println("123123");
    }
}

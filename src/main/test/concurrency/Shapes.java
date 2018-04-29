package concurrency;

/**
 * Created by li on 9/27/17.
 */
public class Shapes {

    public static void main(String[] args) {
    }
}
class Shape{
    void draw(){
        System.out.println(this+".draw()");
    }
}

class Circle extends Shape{
    @Override
    public String toString() {
        return "Circle";
    }
}

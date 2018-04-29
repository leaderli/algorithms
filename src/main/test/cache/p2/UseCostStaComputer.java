package cache.p2;

public class UseCostStaComputer implements Computable<String,String>{
    @Override
    public String compute(String s) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s.hashCode()+"="+new String(s).hashCode());
        return new String(s);
    }
}

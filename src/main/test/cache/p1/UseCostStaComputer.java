package cache.p1;

import java.util.HashMap;
import java.util.Map;

public class UseCostStaComputer {
    private static final Map<String,Integer> MAP = new HashMap<>();

    public synchronized Integer compute(String userId){
        Integer result =null;
        result = MAP.get(userId);
        if(result==null){
            result = doCompute(userId);
            MAP.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Integer(userId);
    }

}

package cache;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class JSTest {
    public static void main(String[] args) {
        ScriptEngineManager manager =  new ScriptEngineManager();
        for (ScriptEngineFactory scriptEngineFactory : manager.getEngineFactories()) {
            System.out.println(scriptEngineFactory.getNames());
        }
    }
}

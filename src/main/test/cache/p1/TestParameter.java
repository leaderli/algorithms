package cache.p1;


import org.json.JSONObject;
import org.json.XML;

public class TestParameter {
    public static void main(String[] args) {
        String s = "{\"root\":[{\"string\":\"1\"},{\"number\":[\"int\",\"double\"]}]}\n";
        JSONObject o = new JSONObject(s);
        String xml = XML.toString(o);
        System.out.println(xml);
    }
}

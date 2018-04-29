package concurrency;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * Created by li on 12/4/17.
 */
public class Test_Jedis extends TestCase{
    public void test() throws IOException {
        Jedis jedis = new Jedis();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        Person person = new Person(1,"fuck");
        Person person2 = new Person(2,"fuck");
        person.setPerson(person2);
        System.out.println(person);
        oos.writeObject(person);
        byte[] bytes = bos.toByteArray();
        System.out.println(jedis.set("person".getBytes(),bytes));
    }

    public void test2() throws IOException, ClassNotFoundException {
       Jedis jedis = new Jedis();
       byte[] bytes = jedis.get("person".getBytes());
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bi);
        Person p = (Person) in.readObject();
        System.out.println(p);

    }
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.set("nana","1");
        System.out.println(jedis.get("nana"));
    }
}

package concurrency;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by li on 12/13/17.
 */
public class PriorityQueueTest {
    static class Person{
        int id;
        String name;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person>  integerQueue = new PriorityBlockingQueue<Person>(7, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.id-o2.id;
            }
        });

        Random random = new Random();
        for (int i = 0; i <1 ; i++) {
            ThreadQueue threadQueue = new ThreadQueue(integerQueue);
            threadQueue.start();
        }

        while (true){
            System.out.println(integerQueue.take());
        }
    }
    private static class ThreadQueue extends Thread{
        PriorityBlockingQueue queue;
        public ThreadQueue(PriorityBlockingQueue queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            Random random = new Random();
            while (true){
                try {
                    Thread.sleep(random.nextInt(1000)+1000);
                    for (int i = 0; i <5 ; i++) {
                    Person person = new  Person();
                    int id = random.nextInt(50);
                    person.id= id;
                    person.name=id+"P";
                    queue.put(person);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

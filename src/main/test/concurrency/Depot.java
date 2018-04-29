package concurrency;

public class Depot {
    private int capacity;

    private int size;

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public synchronized void product(int val) {
        if (val < 0)
            throw new RuntimeException("val must > 0");
        while (size >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        val = size + val < capacity ? val : capacity - size;
        size += val;
        try {
            Thread.sleep(val * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("has product " + val);
        notifyAll();
    }

    public synchronized void consume(int val) {
        if (val < 0)
            throw new RuntimeException("val must > 0");
        while (size <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        val = val > size ? size : val;
        size -= val;
        try {
            Thread.sleep(val * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("has consume" + val);
        notifyAll();
    }

    private static class Product {
        private Depot producer;

        public Product(Depot producer) {
            this.producer = producer;
        }

        public void product(int val) {
            new Thread(() -> {
                producer.product(val);
            }).start();
        }
    }
    private static class Customer{
        private Depot producer;

        public Customer(Depot producer) {
            this.producer = producer;
        }

        public void consume(int val) {
            new Thread(() -> {
                producer.consume(val);
            }).start();
        }
    }

    public static void main(String[] args) {
        Depot depot = new Depot(200);
        Product product = new Product(depot);
        Customer customer = new Customer(depot);
        product.product(60);
        product.product(150);
        product.product(150);
        product.product(150);
        customer.consume(100);
        customer.consume(100);
        customer.consume(100);
    }
}

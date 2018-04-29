package cache.p2;

import java.util.Map;
import java.util.concurrent.*;

public class Memorize<K, V> implements Computable<K, V> {
    private Computable<K, V> computer;

    private final Map<K, Future<V>> CACHE = new ConcurrentHashMap<>();

    Memorize(Computable<K, V> computer) {
        this.computer = computer;
    }

    @Override
    public V compute(K k) {
        while (true) {
            Future<V> f = CACHE.get(k);
            if (f == null) {
                FutureTask<V> ft = new FutureTask<>(() -> computer.compute(k));
                f = CACHE.putIfAbsent(k, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (InterruptedException e) {
                CACHE.remove(k, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

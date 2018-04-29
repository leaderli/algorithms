package cache.p2;

public interface Computable<K,V> {
    V compute(K k);
}
